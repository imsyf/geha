package im.syf.geha.ui.profile

import androidx.annotation.StringRes
import im.syf.geha.R
import im.syf.geha.ui.profile.parcelable.SomeParcelable

sealed class PageItem(@StringRes val title: Int)

class ParcelablePage(val some: SomeParcelable) : PageItem(R.string.parcelable)
class TextPage(val text: String) : PageItem(R.string.text)
object NothingPage : PageItem(R.string.nothing)
