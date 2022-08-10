package im.syf.geha.ui.profile.list

import androidx.recyclerview.widget.DiffUtil
import im.syf.geha.data.network.response.RepoDto

data class Repo(
    val name: String,
    val description: String,
    val language: String,
    val star: Int,
    val watcher: Int,
    val fork: Int,
    val url: String,
) {
    companion object {
        val DIFFER = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem == newItem
            }
        }
    }
}

fun RepoDto.toRepo(): Repo = Repo(
    name,
    description,
    language,
    star = stargazers_count,
    watcher = watchers_count,
    fork = forks_count,
    url = html_url,
)
