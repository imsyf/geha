package im.syf.geha.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProfilePagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val items: List<PageItem>,
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment = when (val page = items[position]) {
    }
}
