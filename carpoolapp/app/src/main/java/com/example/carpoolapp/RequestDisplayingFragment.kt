import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import android.widget.Toast
import com.example.carpoolapp.*


/**
 * A simple [Fragment] subclass.
 * Use the [RequestDisplayingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RequestDisplayingFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var requestArrayList: ArrayList<User>
    private lateinit var requestAdapter: RequestAdapter
    private lateinit var db: DatabaseReference
    private lateinit var selectedUsers: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_request_displaying, container, false)
        Log.d(TAG, "Fragment view inflated successfully")
        recyclerView = rootView.findViewById(R.id.requestrecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)

        requestArrayList = ArrayList()
        requestAdapter = RequestAdapter(requestArrayList) { user ->
            // Handle item selection
        }
        selectedUsers = requestAdapter.getSelectedUsers()
        val achen = Offers(
            userName = "AChen",
            name = "Alice Chen",
            email = "alicechen@example.com",
            phoneNumber = "123-456-7890",
            address = "1280 Main St W, Hamilton, ON L8S 4L8"
        )

        val jlipp = Offers(
            userName = "JLipp",
            name = "Jackson Lippert",
            email = "jl@mcmaster.ca",
            phoneNumber = "123456788",
            address = "1280 Main St W, Hamilton, ON L8S 4L8"
        )

        val spiderman = Offers(
            userName = "spiderman",
            name = "Tom Holland",
            email = "therealspidey@mcu.com",
            phoneNumber = "1112223333",
            address = "25 Dalewood Avenue, Hamilton, ON L8S 1C2"
        )





        recyclerView.adapter = requestAdapter
        Log.d(TAG, "Adapter set on RecyclerView")

        // initialize Firebase database reference
        db = Firebase.database.reference.child("Users")
        Log.d(TAG, "Connecting to Firebase database...")

        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                requestArrayList.clear()

                for (requestSnapshot in snapshot.children) {
                    val request = requestSnapshot.getValue(User::class.java)
                    requestArrayList.add(request!!)
                }

                requestAdapter.notifyDataSetChanged()
                Log.d(TAG, "Requests retrieved successfully")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "Error fetching requests", error.toException())
            }
        })


        var confirmridebtn = rootView.findViewById<Button>(R.id.confirmrideButton)
        confirmridebtn.setOnClickListener {
            if (selectedUsers.isNotEmpty()) {
                // At least one user has been selected, proceed to final trip display fragment
                Log.d(TAG, "Selected users: ${selectedUsers.map { it.userName }}")
                val bundle = Bundle()

                // Pass the list of selected users to the final trip display fragment
                val selectedUserNames = selectedUsers.map { it.userName }
                bundle.putStringArrayList("selectedUsers", ArrayList(selectedUserNames))

                val transaction: FragmentTransaction = getParentFragmentManager().beginTransaction()
                val finalTripDisplayFragment = FinalTripDisplayFragment()
                finalTripDisplayFragment.arguments = bundle
                transaction.replace(R.id.FragmentContainer, finalTripDisplayFragment)
                transaction.commit()
            } else {
                // No user has been selected, show an error message
                Toast.makeText(activity, "Please select at least one user to proceed", Toast.LENGTH_SHORT).show()
            }
        }

        return rootView
    }

    companion object {
        private const val TAG = "RequestDisplayingFrag"

        @JvmStatic
        fun newInstance() = RequestDisplayingFragment()
    }
}