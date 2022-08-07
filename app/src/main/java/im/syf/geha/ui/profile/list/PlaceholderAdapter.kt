package im.syf.geha.ui.profile.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import im.syf.geha.databinding.ItemPlaceholderBinding

class PlaceholderAdapter(
    private val listType: ListType,
) : RecyclerView.Adapter<PlaceholderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPlaceholderBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val label = when (listType) {
            is FollowersListType -> "Followers"
            is FollowingListType -> "Following"
            is RepositoryListType -> "Repository"
        }

        holder.bind("$label ${position + 1}")
    }

    override fun getItemCount(): Int = listType.itemCount

    class ViewHolder(
        private val itemBinding: ItemPlaceholderBinding,
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(text: String) = with(itemBinding) {
            textView.text = text
        }
    }
}
