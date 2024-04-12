package viewmodel

import Repository.PostRepository
import Repository.PostRepositoryInMemoryImpl
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dto.Post

private  val empty = Post(
    id = 0,
    content = "",
    author = "",
    likedByMe = false,
    published = "",
    likeCount = 0,
    shareCount = 0
)
class  PostViewModel : ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImpl()

    val data = repository.getAll()

    val edited = MutableLiveData(empty)
    fun save(){
        edited.value?.let{
            repository.save(it)
        }
        edited.value = empty
    }
    fun edit(post: Post) {
        edited.value = post
    }
    fun changeContent(content: String) {
        edited.value?.let {
            val text = content.trim()
            if (it.content == text) {
                return
            }
edited.value = it.copy(content = text)
        }
    }
    fun like(id: Long) = repository.like(id)
    fun share(id: Long) = repository.share(id)
    fun removeById(id:Long) = repository.removeById(id)


}