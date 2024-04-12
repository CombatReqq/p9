package dto
    data class  Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likedByMe: Boolean,
    val likeCount: Int,
    val shareCount: Int
    )
