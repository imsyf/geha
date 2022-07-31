package im.syf.geha.ui.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import im.syf.geha.Geha
import im.syf.geha.data.DummyUser
import im.syf.geha.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout with view binding
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listAdapter = UserListAdapter(::navigate)

        with(binding.userRecyclerView) {
            adapter = listAdapter
            setHasFixedSize(true)
        }

        // Get list of dummy users, then map them to this screen data model
        val users = (application as Geha).dummyDataSource.dummyUsers
            .map(DummyUser::toUser)

        listAdapter.submitList(users)
    }

    private fun navigate(user: User) {
    }
}
