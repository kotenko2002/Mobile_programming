import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lab9.models.DbPost

class DbPostViewModel : ViewModel() {
    private val _newPost = MutableLiveData<DbPost>()
    val newPost: LiveData<DbPost> get() = _newPost

    fun addNewPost(post: DbPost) {
        _newPost.value = post
    }
}
