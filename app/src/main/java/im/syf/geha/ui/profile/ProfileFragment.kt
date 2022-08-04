package im.syf.geha.ui.profile

import android.content.ActivityNotFoundException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import im.syf.geha.Geha
import im.syf.geha.R
import im.syf.geha.data.DummyUser
import im.syf.geha.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private val args: ProfileFragmentArgs by navArgs()

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

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

        // Get the more appropriately-shaped data model for this screen
        val userProfile: UserProfile = (activity?.application as Geha).dummyDataSource
            .dummyUsers[args.user.id]
            .let(DummyUser::toUserProfile)

        // Define tab items
        val items: List<PageItem> = with(userProfile) {
            listOf(
                RepositoryPage(username, repository.toInt()),
                FollowingPage(username, following.toInt()),
                FollowersPage(username, followers.toInt()),
            )
        }

        with(binding) {
            // Bind data model to the layout
            profile = userProfile

            // Set up view pager and tab layout
            pager.adapter = ProfilePagerAdapter(this@ProfileFragment, items)
            TabLayoutMediator(tabs, pager) { tab, position ->
                tab.text = getString(items[position].title)
            }.attach()

            shareButton.setOnClickListener { onShare(userProfile) }
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
