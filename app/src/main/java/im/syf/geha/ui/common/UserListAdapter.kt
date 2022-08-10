package im.syf.geha.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import im.syf.geha.R
import im.syf.geha.databinding.ItemUserListBinding

class UserListAdapter(
    private val onItemClicked: (User) -> Unit,
) : ListAdapter<User, UserListAdapter.ViewHolder>(User.DIFFER) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)

        holder.bind(user)
        holder.itemView.setOnClickListener { onItemClicked(user) }
    }

    class ViewHolder(
        private val itemBinding: ItemUserListBinding,
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(user: User) = with(itemBinding) {
            with(avatarImageView) {
                load(user.avatarUrl)
                contentDescription = context.getString(R.string.avatar_of_user, user.username)
            }
            usernameTextView.text = user.username
            accountTypeTextView.text = user.accountType
        }
    }
}
