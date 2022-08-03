package im.syf.geha.ui.search

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import im.syf.geha.Geha
import im.syf.geha.data.DummyUser
import im.syf.geha.databinding.ActivitySearchBinding
import im.syf.geha.ui.profile.ProfileActivity
import im.syf.geha.ui.profile.ProfileActivity.Companion.EXTRA_USER_KEY

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
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
        val intent = Intent(this, ProfileActivity::class.java).apply {
            putExtra(EXTRA_USER_KEY, user)
        }
        startActivity(intent)
    }
}
