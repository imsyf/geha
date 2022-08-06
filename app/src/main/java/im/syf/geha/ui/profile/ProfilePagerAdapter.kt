package im.syf.geha.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import im.syf.geha.ui.profile.nothing.NothingFragment
import im.syf.geha.ui.profile.parcelable.ParcelableFragment
import im.syf.geha.ui.profile.text.TextFragment

class ProfilePagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val items: List<PageItem>,
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment = when (val page = items[position]) {
        NothingPage -> NothingFragment()
        is TextPage -> TextFragment.newInstance(page.text)
        is ParcelablePage -> ParcelableFragment.newInstance(page.some)
    }
}
