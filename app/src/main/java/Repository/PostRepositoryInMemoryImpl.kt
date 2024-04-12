package Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dto.Post

class PostRepositoryInMemoryImpl : PostRepository {
    private var nextId = 1L
    private var post = listOf(
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растем сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остается с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен -> http://netology.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
            likeCount = 999,
            shareCount = 11999
        ),
        Post(
            id = nextId++,
            author = "Infinix.Россия",
            content = "❄️Представляем передовую технологию Extreme-Temp❄️\n" +
                    "\n" + "Для решения проблемы зарядки смартфонов в холодных условиях мы объединили усилия с ведущими поставщиками технологий и разработали специальную батарею Extreme-Temp. Благодаря применению передовых разработок батарея Extreme-Temp может бесперебойно работать даже при температурах до -20°C. ->  https://t.me/s/infinix_russia",
            published = "22 марта в 13:30",
            likedByMe = false,
            likeCount = 19,
            shareCount = 3
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растем сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остается с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен -> http://netology.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
            likeCount = 999,
            shareCount = 11999
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растем сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остается с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен -> http://netology.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
            likeCount = 999,
            shareCount = 11999
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растем сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остается с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен -> http://netology.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
            likeCount = 999,
            shareCount = 11999
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растем сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остается с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен -> http://netology.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
            likeCount = 999,
            shareCount = 11999
        )
    )

    private val data = MutableLiveData(post)

    override fun getAll(): LiveData<List<Post>> = data

    override fun save(posts: Post) {
        if (posts.id == 0L)
        {
            post = listOf(
                posts.copy(
                    id = nextId++,
                    author = "Me",
                    likedByMe = false,
                    published = "now",
                    likeCount = 0,
                    shareCount = 0

                )
            ) + post
        data.value = post
        return
    }
    post = post.map{
            if (it.id != posts.id) it else it.copy(content = posts.content)
        }
        data.value = post
    }

    override fun like(id: Long) {
        post = post.map {
            if (it.id == id && it.likedByMe)
            { it.copy(likedByMe = !it.likedByMe, likeCount = it.likeCount - 1)}else
            if (it.id == id && !it.likedByMe) {
                it.copy(likedByMe = !it.likedByMe, likeCount = it.likeCount + 1)

            }else
                it
        }

        data.value = post
    }

    override fun share(id: Long) {
        post = post.map {
           if (it.id != id) {
               it
           }
            else {
                it.copy(shareCount = it.shareCount +1)
           }


        }
        data.value = post
    }
    override fun removeById(id: Long) {
        post = post.filter { it.id != id }
        data.value = post
    }

}