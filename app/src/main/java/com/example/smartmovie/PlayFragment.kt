package com.example.smartmovie

import PlaylistAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartmovie.databinding.FragmentPlayBinding

class PlayFragment : Fragment() {
    private var _binding: FragmentPlayBinding? = null
    private val binding get() = _binding!!
    private lateinit var playlistAdapter: PlaylistAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playlistAdapter = PlaylistAdapter(emptyList<MovieResult>())
        binding.playlistView.adapter = playlistAdapter
        binding.playlistView.layoutManager = LinearLayoutManager(context)

        val movies = getMovies()

        playlistAdapter.updateItems(movies)
    }

    private fun getMovies(): List<MovieResult> {
        Log.d("PlayFragment.getPlaylist", "TODO: Fetch db movies")
        return listOf(
            MovieResult(
                "1",
                "Foo Bar",
                "Foo Bar",
                "Foo Bar",
                "Foo Bar",
                0.0
            ),
            MovieResult(
                "1",
                "Foo Bar",
                "Foo Bar",
                "Foo Bar",
                "Foo Bar",
                0.0
            ),
            MovieResult(
                "1",
                "Foo Bar",
                "Foo Bar",
                "Foo Bar",
                "Foo Bar",
                0.0
            ),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}