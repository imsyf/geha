package im.syf.geha.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import im.syf.geha.Geha
import im.syf.geha.R
import im.syf.geha.data.DummyUser
import im.syf.geha.databinding.ViewListBinding

class SearchFragment : Fragment() {

    private var _binding: ViewListBinding? = null
    private val binding get() = _binding!!

    private val listAdapter = UserListAdapter(::navigate)

    // Get list of dummy users, then map them to this screen data model
    private val users: List<User> by lazy {
        (activity?.application as Geha).dummyDataSource.dummyUsers.map(DummyUser::toUser)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ViewListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOptionsMenu()

        with(binding.recyclerView) {
            adapter = listAdapter
            setHasFixedSize(true)
        }

        listAdapter.submitList(users)
    }

    private fun navigate(user: User) {
        val directions = SearchFragmentDirections.toProfileFragment(user, user.username)
        findNavController().navigate(directions)
    }

    private fun setupOptionsMenu() {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.search_menu, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    return when (menuItem.itemId) {
                        R.id.search_menu_item -> {
                            setupSearchView(menuItem)
                            true
                        }
                        else -> false
                    }
                }
            },
            viewLifecycleOwner, Lifecycle.State.RESUMED
        )
    }

    private fun setupSearchView(search: MenuItem) {
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}