package ru.btpit.p2

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dto.Post
import ru.btpit.p2.databinding.ActivityMainBinding
interface OnInteractionListener{
    fun onLike(post: Post){}
    fun onShare(post: Post){}
    fun onEdit(post: Post) {}
    fun onRemove(post: Post) {}
}
//typealias OnLikeListener = (post: Post) -> Unit
//typealias OnShareListener = (post: Post) -> Unit
//typealias OnRemoveListener = (post: Post) ->Unit
class PostsActivity(private val onInteractionListener: OnInteractionListener
                  ) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding =
            ActivityMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

class PostViewHolder(
    private val binding: ActivityMainBinding,
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root){
    fun bind (post: Post){
        binding.apply {
            author.text = post.author
                    published.text = post.published
                    content.text = post.content
                    share.text = formatNumber(post.shareCount)
                    like.text = formatNumber(post.likeCount)
                    like.isChecked = post.likedByMe
//                    LikeCount.text = formatNumber(post.likeCount)
                    //ShareCount.text = formatNumber(post.shareCount)
            menu.setOnClickListener{
                PopupMenu(it.context, it).apply { inflate(R.menu.popmenu)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId){
                            R.id.remove ->{
                                onInteractionListener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }
                            else -> false
                        }
                    }}.show()

            }



                    like.setOnClickListener{
                        onInteractionListener.onLike(post) }

                     share.setOnClickListener{
                          onInteractionListener.onShare(post) }


        }
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<Post>(){
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return  oldItem == newItem
    }
}
private fun formatNumber(number: Int): String {
    return when {
        number >= 1000000 -> {
            val value = number / 1000000
            val remainder = number % 1000000
            if (remainder > 0) {
                if (remainder >= 100000) {
                    String.format("%.1f M", value + remainder / 1000000.0)
                } else {
                    String.format("%d.%d M", value, remainder / 100000)
                }
            } else {
                "$value M"
            }
        }
        number in 1000..9999 -> {
            String.format("%.1fK", number / 1000.0)
        }
        number >= 10000 -> {
            String.format("%dK", number / 1000)
        }
        else -> number.toString()
    }

}
