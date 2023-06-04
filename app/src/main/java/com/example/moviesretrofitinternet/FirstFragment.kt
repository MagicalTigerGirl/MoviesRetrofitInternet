package com.example.moviesretrofitinternet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.moviesretrofitinternet.adapter.MovieAdapter
import com.example.moviesretrofitinternet.databinding.FragmentFirstBinding
import com.example.moviesretrofitinternet.model.Movie
import com.example.moviesretrofitinternet.model.MovieClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

class FirstFragment : Fragment(), MovieAdapter.OnManageMovieListener {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        // Opción 1: con hilos secundarios
        thread {
            val listMovies = MovieClient.service.listPopularMovies(getString(R.string.api_key))
            val body = listMovies.execute().body()

            getActivity()?.runOnUiThread(Runnable {
                if (body != null)
                    adapter.movies = body.results
                adapter.notifyDataSetChanged()
            })
        }

        // Opción 2: con corrutinas
        lifecycleScope.launch {
            val listMovies = MovieClient.service.listPopularMovies(getString(R.string.api_key))
            val body = withContext(Dispatchers.IO) {listMovies.execute().body() }
            if (body != null) {
                adapter.movies = body.results
                adapter.notifyDataSetChanged()
            }
        }

    }

    private fun initRecyclerView() {
        adapter = MovieAdapter(this)
        binding.rvMovie.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onShowTitleMovie(movie: Movie) {
        Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show()
    }

    override fun onNavigateMovie(movie: Movie) {
        val bundle = Bundle()
        bundle.putParcelable(Movie.TAG, movie)
        NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
    }
}