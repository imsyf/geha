package im.syf.geha.ui.profile.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import im.syf.geha.Geha
import im.syf.geha.databinding.ViewListBinding
import im.syf.geha.ui.common.StatusAdapter
import im.syf.geha.ui.profile.ProfileFragmentDirections
import im.syf.geha.ui.profile.list.ListViewModel.State
import im.syf.geha.ui.search.User
import im.syf.geha.ui.search.UserListAdapter

class ListFragment : Fragment() {

    private var _binding: ViewListBinding? = null
    private val binding get() = _binding!!

    private val listTypeArg: ListType by lazy {
        requireNotNull(requireArguments().getParcelable(ARG_PROPERTY_KEY))
    }

    private val viewModel: ListViewModel by viewModels {
        viewModelFactory {
            initializer {
                val app = activity?.application as Geha
                ListViewModel(listTypeArg, app.gitHubService)
            }
        }
    }

    private val statusAdapter = StatusAdapter()
    private val listAdapter: RecyclerView.Adapter<out RecyclerView.ViewHolder> by lazy {
        when (listTypeArg) {
            is FollowersListType, is FollowingListType -> UserListAdapter(::navigate)
            is RepositoryListType -> RepoListAdapter(::open)
        }
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

        binding.recyclerView.setHasFixedSize(true)

        /**
         * Loading indicator looks weird when it's right under the tab layout, so
         * move it to the bottom of the screen instead
         */
        binding.progressIndicator.updateLayoutParams<ConstraintLayout.LayoutParams> {
            topToTop = ConstraintLayout.LayoutParams.UNSET
            bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
        }

        viewModel.state.observe(viewLifecycleOwner, ::render)
    }

    private fun render(state: State) {
        with(binding) {
            progressIndicator.isVisible = state is State.Loading

            when (state) {
                State.Loading -> {
                    recyclerView.adapter = statusAdapter
                    statusAdapter.onLoading()
                }
                State.Empty -> {
                    recyclerView.adapter = statusAdapter
                    statusAdapter.onEmpty()
                }
                State.Error -> {
                    recyclerView.adapter = statusAdapter
                    statusAdapter.onError()
                }
                is State.RepoData -> {
                    recyclerView.adapter = listAdapter
                    (listAdapter as RepoListAdapter).submitList(state.repos)
                }
                is State.UserData -> {
                    recyclerView.adapter = listAdapter
                    (listAdapter as UserListAdapter).submitList(state.users)
                }
            }
        }
    }

    private fun navigate(user: User) {
        val directions = ProfileFragmentDirections.toProfileFragment(user, user.username)
        findNavController().navigate(directions)
    }

    private fun open(repo: Repo) {
    }

    companion object {

        private const val ARG_PROPERTY_KEY = "arg_property_key"

        @JvmStatic
        fun newInstance(listType: ListType): ListFragment {
            return ListFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PROPERTY_KEY, listType)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
