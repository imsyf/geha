package im.syf.geha.ui.profile

import android.content.ActivityNotFoundException
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import im.syf.geha.Geha
import im.syf.geha.R
import im.syf.geha.data.DummyUser
import im.syf.geha.databinding.ActivityProfileBinding
import im.syf.geha.ui.profile.parcelable.SomeParcelable
import im.syf.geha.ui.search.User

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout with data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)

        // Retrieve argument passed via intent
        val user: User = requireNotNull(intent.getParcelableExtra(EXTRA_USER_KEY))
        // Get the more appropriately-shaped data model for this screen
        val profile: UserProfile = (application as Geha).dummyDataSource.dummyUsers[user.id]
            .let(DummyUser::toUserProfile)

        // Set up view pager
        val items: List<PageItem> = listOf(
            ParcelablePage(SomeParcelable(false, 123, "abc")),
            TextPage("abc"),
            NothingPage,
        )
        binding.pager.adapter = ProfilePagerAdapter(supportFragmentManager, lifecycle, items)

        // Set up tab layout
        TabLayoutMediator(binding.tabs, binding.pager) { tab, position ->
            tab.text = getString(items[position].title)
        }.attach()

        // Enable up button for backward navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Customize toolbar title
        title = profile.username

        binding.shareButton.setOnClickListener { onShare(profile) }

        // Bind data model to the layout
        binding.profile = profile
    }

    private fun onShare(profile: UserProfile) {
        val intentBuilder = ShareCompat.IntentBuilder(this)
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

    companion object {
        const val EXTRA_USER_KEY = "extra_user_key"
    }
}
