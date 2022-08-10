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
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import im.syf.geha.Geha
import im.syf.geha.R
import im.syf.geha.databinding.ViewListBinding
import im.syf.geha.ui.common.StatusAdapter
import im.syf.geha.ui.search.SearchViewModel.State

class SearchFragment : Fragment() {

    private var _binding: ViewListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels {
        viewModelFactory {
            initializer {
                val app = activity?.application as Geha
                SearchViewModel(app.gitHubService)
            }
        }
    }

    private val listAdapter = UserListAdapter(::navigate)
    private val statusAdapter = StatusAdapter()

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
        binding.recyclerView.setHasFixedSize(true)
        viewModel.state.observe(viewLifecycleOwner, ::render)
    }

    private fun render(state: State) {
        binding.progressIndicator.isVisible = state is State.Loading

        when (state) {
            State.Initial -> {
                binding.recyclerView.adapter = statusAdapter
                statusAdapter.onInitial()
            }
            State.Loading -> {
                binding.recyclerView.adapter = statusAdapter
                statusAdapter.onLoading()
            }
            State.Error -> {
                binding.recyclerView.adapter = statusAdapter
                statusAdapter.onError()
            }
            is State.Success -> {
                binding.recyclerView.adapter = listAdapter
                listAdapter.submitList(state.users)
            }
        }
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
        // Reset state back to its initial when search view is closed
        search.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                viewModel.reset()
                return true
            }
        })

        val searchView = search.actionView as SearchView
        // Capture user input, use it as search query
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.onQuery("$query")
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
