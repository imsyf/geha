package im.syf.geha.ui.profile

import android.content.ActivityNotFoundException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import im.syf.geha.Geha
import im.syf.geha.R
import im.syf.geha.databinding.FragmentProfileBinding
import im.syf.geha.ui.profile.ProfileViewModel.State

class ProfileFragment : Fragment() {

    private val args: ProfileFragmentArgs by navArgs()

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels {
        viewModelFactory {
            initializer {
                val app = activity?.application as Geha
                ProfileViewModel(args.username, app.gitHubService)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, ::render)
    }

    private fun render(state: State) {
        with(binding) {
            progressIndicator.isVisible = state is State.Loading

            with(state !is State.Success) {
                statusImage.isVisible = this
                statusText.isVisible = this
            }

            with(state is State.Success) {
                appBarLayout.isVisible = this
                pager.isVisible = this
            }

            when (state) {
                State.Loading -> {
                    statusImage.setImageResource(R.drawable.ic_status_loading)
                    statusText.setText(R.string.status_loading)
                }
                State.Error -> {
                    statusImage.setImageResource(R.drawable.ic_status_error)
                    statusText.setText(R.string.status_error)
                }
                is State.Success -> {
                    // Bind data model to the layout
                    bindData(state.userProfile)
                }
            }
        }
    }

    private fun FragmentProfileBinding.bindData(profile: UserProfile) {
        // Define tab items
        val items: List<PageItem> = with(profile) {
            listOf(
                RepositoryPage(username),
                FollowingPage(username),
                FollowersPage(username),
            )
        }

        // Set up view pager and tab layout
        pager.adapter = ProfilePagerAdapter(this@ProfileFragment, items)
        TabLayoutMediator(tabs, pager) { tab, position ->
            tab.text = getString(items[position].title)
        }.attach()

        with(avatarImageView) {
            load(profile.avatarUrl)
            contentDescription = getString(R.string.avatar_of_user, profile.username)
        }
        repositoryTextView.text = "${profile.repository}"
        followingTextView.text = "${profile.following}"
        followersTextView.text = "${profile.followers}"
        nameTextView.text = profile.name
        companyTextView.text = profile.company
        locationTextView.text = profile.location

        shareButton.setOnClickListener { onShare(profile) }
    }

    private fun onShare(profile: UserProfile) {
        val intentBuilder = ShareCompat.IntentBuilder(requireContext())
            .setType("text/plain")
            .setText(getString(R.string.share_text, profile.name, profile.username))

        try {
            intentBuilder.startChooser()
        } catch (e: ActivityNotFoundException) {
            Snackbar.make(binding.root, R.string.sharing_failed, Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.dismiss)) {}
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
