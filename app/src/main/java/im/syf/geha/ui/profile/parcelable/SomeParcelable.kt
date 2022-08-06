package im.syf.geha.ui.profile.parcelable

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SomeParcelable(
    val bool: Boolean,
    val int: Int,
    val string: String,
) : Parcelable
