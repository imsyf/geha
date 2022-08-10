package im.syf.geha.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import im.syf.geha.R
import im.syf.geha.databinding.ItemStatusBinding
import im.syf.geha.ui.common.StatusAdapter.Status
import im.syf.geha.ui.common.StatusAdapter.Status.Initial
import im.syf.geha.ui.common.StatusAdapter.ViewHolder

class StatusAdapter : ListAdapter<Status, ViewHolder>(DIFFER) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemStatusBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder.binding) {
        val status = getItem(position)

        @DrawableRes
        val drawableRes = when (status) {
            Initial -> R.drawable.ic_status_initial
        }

        @StringRes
        val stringRes = when (status) {
            Initial -> R.string.status_initial
        }

        statusImage.setImageResource(drawableRes)
        statusText.setText(stringRes)
    }

    fun onInitial() {
        submitList(listOf(Initial))
    }

    class ViewHolder(val binding: ItemStatusBinding) : RecyclerView.ViewHolder(binding.root)

    sealed class Status {
        object Initial : Status()
    }

    companion object {
        private val DIFFER = object : DiffUtil.ItemCallback<Status>() {
            override fun areItemsTheSame(oldItem: Status, newItem: Status): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Status, newItem: Status): Boolean {
                return oldItem == newItem
            }
        }
    }
}
