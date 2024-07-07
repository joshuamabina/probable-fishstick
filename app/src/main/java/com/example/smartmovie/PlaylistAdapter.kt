import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartmovie.MovieResult
import com.example.smartmovie.databinding.ItemMovieBinding

class PlaylistAdapter(private var movieResults: List<MovieResult>) :
    RecyclerView.Adapter<PlaylistAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieResult: MovieResult) {
            binding.textOriginalTitle.text = movieResult.originalTitle
            binding.textVoteAverage.text = movieResult.voteAverage.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieResults[position])
    }

    override fun getItemCount(): Int = movieResults.size

    fun updateItems(newMovieResults: List<MovieResult>) {
        movieResults = newMovieResults
    }
}
