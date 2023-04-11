import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carpoolapp.MyAdapter
import com.example.carpoolapp.R
import com.example.carpoolapp.User

class OffersDisplayingFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<User>
    private lateinit var myAdapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_offers_displaying, container, false)
        Log.d(TAG, "Fragment view inflated successfully")
        recyclerView = rootView.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf()
        myAdapter = MyAdapter(userArrayList)
        recyclerView.adapter = myAdapter
        Log.d(TAG, "Adapter set on RecyclerView")

        // Add some dummy data
        userArrayList.add(User("johndoe", "John Doe", "johndoe@gmail.com", "555-5555", "123 Main St", "4.5"))
        userArrayList.add(User("janedoe", "Jane Doe", "janedoe@gmail.com", "555-5555", "456 Elm St", "3.8"))

        myAdapter.notifyDataSetChanged()
        Log.d(TAG, "Local data added successfully")

        return rootView
    }

    companion object {
        private const val TAG = "OffersDisplayingFragment"

        @JvmStatic
        fun newInstance() = OffersDisplayingFragment()
    }
}
