package im.syf.geha.ui.profile

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import im.syf.geha.ui.profile.list.ListFragment

class ProfilePagerAdapter(
    fragment: Fragment,
    private val items: List<PageItem>,
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment = when (val page = items[position]) {
        is FollowersPage -> ListFragment.newInstance(page.followers)
        is FollowingPage -> ListFragment.newInstance(page.following)
        is RepositoryPage -> ListFragment.newInstance(page.repository)
    }
}
