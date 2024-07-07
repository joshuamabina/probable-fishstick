package com.example.smartmovie

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.smartmovie.databinding.LayoutMovieBinding

class MoviePagerAdapter(private var movieResults: List<MovieResult>) : PagerAdapter() {
    override fun getCount(): Int = movieResults.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutMovieBinding = LayoutMovieBinding.inflate(LayoutInflater.from(container.context), container, false)
        val movie = movieResults[position]

        Glide.with(container.context)
            .load("https://image.tmdb.org/t/p/w500/${movie.posterPath}")
            .fitCenter()
            .into(layoutMovieBinding.moviePoster)

        container.addView(layoutMovieBinding.root)

        val likeButton = layoutMovieBinding.likeButton
        likeButton.setOnClickListener {
            Log.d("MoviePagerAdapter.like", "I Like This:${movie.originalTitle}")
            nextItem(container, position)
        }

        val dislikeButton = layoutMovieBinding.dislikeButton
        dislikeButton.setOnClickListener {
            Log.d("MainActivity.dislikeButton.after", "after: I Dislike This ${movie.originalTitle}")
            nextItem(container, position)
        }

        return layoutMovieBinding.root
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    fun updateItems(newMovieResults: List<MovieResult>) {
        movieResults = newMovieResults
        notifyDataSetChanged()
    }

    private fun nextItem(container: ViewGroup, currentPosition: Int) {
        if (currentPosition < movieResults.size - 1) {
            val newPosition = currentPosition + 1

            val fragment = (container.context as AppCompatActivity)
                .supportFragmentManager
                .findFragmentById(R.id.fragment_container) as? ExploreFragment

            fragment?.run {
                activity?.runOnUiThread {
                    setCurrentMovie(newPosition)
                    Log.d("MoviePagerAdapter.like", "Next movie:${movieResults[newPosition].originalTitle}")
                }
            }
        } else {
            Log.d("MoviePagerAdapter.nextItem", "TODO: Get next page")
        }
    }
}