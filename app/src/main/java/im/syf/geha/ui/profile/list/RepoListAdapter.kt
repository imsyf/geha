package im.syf.geha.ui.profile.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import im.syf.geha.databinding.ItemRepoListBinding

class RepoListAdapter(
    private val onItemClicked: (Repo) -> Unit,
) : ListAdapter<Repo, RepoListAdapter.ViewHolder>(Repo.DIFFER) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRepoListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = getItem(position)

        holder.bind(repo)
        holder.itemView.setOnClickListener { onItemClicked(repo) }
    }

    class ViewHolder(
        private val itemBinding: ItemRepoListBinding,
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(repo: Repo) = with(itemBinding) {
            nameTextView.text = repo.name
            descriptionTextView.text = repo.description

            with(repo.language != null) {
                languageTextView.isVisible = this
                if (this) languageTextView.text = repo.language
            }

            starTextView.text = "${repo.star}"
            watchTextView.text = "${repo.watcher}"
            forkTextView.text = "${repo.fork}"
        }
    }
}
