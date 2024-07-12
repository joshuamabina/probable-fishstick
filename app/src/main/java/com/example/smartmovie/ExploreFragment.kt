package com.example.smartmovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.smartmovie.databinding.FragmentExploreBinding
import kotlinx.coroutines.launch

class ExploreFragment : Fragment() {
    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!
    private lateinit var moviePagerAdapter: MoviePagerAdapter
    private lateinit var exploreMovieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieDao = DB.getDatabase(requireContext()).movieDao()
        val repository = MovieRepository(movieDao)
        exploreMovieViewModel = ViewModelProvider(this, MovieViewModelFactory(repository)).get(MovieViewModel::class.java)

        moviePagerAdapter = MoviePagerAdapter(emptyList(), exploreMovieViewModel)
        binding.moviePager.adapter = moviePagerAdapter

        getMovies()
    }

    private fun getMovies() {
        val movieService = Api.getInstance().create(MovieService::class.java)
        viewLifecycleOwner.lifecycleScope.launch {
            val response = movieService.getMovies(Api.token)
            if (response.isSuccessful) {
                val movieResponse = response.body()
                movieResponse?.let {
                    val movies = it.results
                    moviePagerAdapter.updateItems(movies)
                }
            }
        }
    }

    fun setCurrentMovie(newPosition: Int) {
        binding.moviePager.setCurrentItem(newPosition)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}