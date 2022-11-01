import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikita.filmapp.repository.MoviesRepository
import com.nikita.filmapp.viewModels.MovieViewModel


class MovieViewModelFactory(
        val moviesRepository: MoviesRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(moviesRepository) as T
    }
}