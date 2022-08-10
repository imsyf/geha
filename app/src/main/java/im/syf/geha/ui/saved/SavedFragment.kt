package im.syf.geha.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import im.syf.geha.Geha
import im.syf.geha.databinding.ViewListBinding
import im.syf.geha.ui.common.StatusAdapter
import im.syf.geha.ui.common.User
import im.syf.geha.ui.common.UserListAdapter
import im.syf.geha.ui.saved.SavedViewModel.State

class SavedFragment : Fragment() {

    private var _binding: ViewListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SavedViewModel by viewModels {
        viewModelFactory {
            initializer {
                val app = activity?.application as Geha
                SavedViewModel(app.userProfileDao)
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
        viewModel.state.observe(viewLifecycleOwner, ::render)
    }

    private fun render(state: State) {
        when (state) {
            State.Empty -> {
                binding.recyclerView.adapter = statusAdapter
                statusAdapter.onEmpty()
            }
            is State.Success -> {
                binding.recyclerView.adapter = listAdapter
                listAdapter.submitList(state.users)
            }
        }
    }

    private fun navigate(user: User) {
        val directions = SavedFragmentDirections.toProfileFragment(user, user.username)
        findNavController().navigate(directions)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
