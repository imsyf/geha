package im.syf.geha.ui.profile

import android.content.ActivityNotFoundException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import im.syf.geha.Geha
import im.syf.geha.R
import im.syf.geha.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private val args: ProfileFragmentArgs by navArgs()

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels {
        viewModelFactory {
            initializer {
                val app = activity?.application as Geha
                ProfileViewModel(args.user.id, app.dummyDataSource)
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

        // Define tab items
        val items: List<PageItem> = with(viewModel.userProfile) {
            listOf(
                RepositoryPage(username, repository.toInt()),
                FollowingPage(username, following.toInt()),
                FollowersPage(username, followers.toInt()),
            )
        }

        with(binding) {
            // Bind data model to the layout
            profile = viewModel.userProfile

            // Set up view pager and tab layout
            pager.adapter = ProfilePagerAdapter(this@ProfileFragment, items)
            TabLayoutMediator(tabs, pager) { tab, position ->
                tab.text = getString(items[position].title)
            }.attach()

            shareButton.setOnClickListener { onShare(viewModel.userProfile) }
        }
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
