package im.syf.geha.ui.search

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import im.syf.geha.Geha
import im.syf.geha.R
import im.syf.geha.data.DummyUser
import im.syf.geha.databinding.ActivitySearchBinding
import im.syf.geha.ui.profile.ProfileActivity
import im.syf.geha.ui.profile.ProfileActivity.Companion.EXTRA_USER_KEY

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    private val listAdapter = UserListAdapter(::navigate)

    // Get list of dummy users, then map them to this screen data model
    private val users: List<User> by lazy {
        (application as Geha).dummyDataSource.dummyUsers.map(DummyUser::toUser)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setupSplashScreen()
        super.onCreate(savedInstanceState)

        // Inflate layout with view binding
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.userRecyclerView) {
            adapter = listAdapter
            setHasFixedSize(true)
        }

        listAdapter.submitList(users)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val search = menu.findItem(R.id.search_menu_item)
        // Re-display all data when search view is closed
        search.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                listAdapter.submitList(users)
                return true
            }
        })

        val searchView = search.actionView as SearchView
        // Match in any part of user properties displayed on screen
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val q = "$query"

                val result = users.filter {
                    val name = it.name.contains(q, true)
                    val username = it.username.contains(q, true)
                    val location = it.location.contains(q, true)

                    name || username || location
                }

                listAdapter.submitList(result)
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun navigate(user: User) {
        val intent = Intent(this, ProfileActivity::class.java).apply {
            putExtra(EXTRA_USER_KEY, user)
        }
        startActivity(intent)
    }

    private fun setupSplashScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            installSplashScreen()
        } else {
            setTheme(R.style.Theme_Geha)
        }
    }
}
