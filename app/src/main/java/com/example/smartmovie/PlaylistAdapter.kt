import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartmovie.Movie
import com.example.smartmovie.databinding.ItemMovieBinding

class PlaylistAdapter(private var movies: List<Movie>) :
    RecyclerView.Adapter<PlaylistAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.textOriginalTitle.text = movie.originalTitle
            val maxLength = 17
            val originalTitle = binding.textOriginalTitle.text.toString()
            binding.textOriginalTitle.text = if (originalTitle.length > maxLength) "${originalTitle.substring(0, maxLength)}${"..." }" else originalTitle

            binding.textVoteAverage.text = "%.1f".format(movie.voteAverage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Log.d("PlaylistAdapter.onBindViewHolder", "movies: ${movies.toString()}")
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    fun updateItems(newMovies : List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}
