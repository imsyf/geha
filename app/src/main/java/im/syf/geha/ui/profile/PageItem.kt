package im.syf.geha.ui.profile

import androidx.annotation.StringRes
import im.syf.geha.R

sealed class PageItem(@StringRes val title: Int)

class TextPage(val text: String) : PageItem(R.string.text)
object NothingPage : PageItem(R.string.nothing)
