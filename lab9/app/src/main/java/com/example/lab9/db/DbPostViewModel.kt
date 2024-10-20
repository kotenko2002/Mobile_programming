import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lab9.models.DbPost

class DbPostViewModel : ViewModel() {
    private val _postsChanged = MutableLiveData<Unit>()
    val postsChanged: LiveData<Unit> get() = _postsChanged

    fun notifyChange() {
        _postsChanged.value = Unit
    }
}