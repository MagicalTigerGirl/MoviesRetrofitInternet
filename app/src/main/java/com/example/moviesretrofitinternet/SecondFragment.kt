package com.example.moviesretrofitinternet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.moviesretrofitinternet.databinding.FragmentSecondBinding
import com.example.moviesretrofitinternet.model.Movie

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.movie = requireArguments().getParcelable(Movie.TAG)

        //(activity as MainActivity).supportActionBar?.title = binding.movie?.title
        (activity as MainActivity).supportActionBar?.hide()
        Glide.with(binding.root.context)
            .load("https://image.tmdb.org/t/p/w780/${binding.movie?.poster_path}")
            .into(binding.imgImage)
        //binding.tvSummary.text = binding.movie?.overview
        binding.tvSummary.text = "Al venir al mundo fueron delicadamente mecidas por las manos de la lustral Doniazada, su buena tía, que grabó sus nombres sobre hojas de oro coloreadas de húmedas pedrerías y las cuidó bajo el terciopelo de sus pupilas hasta la adolescencia dura, para esparcirlas después, voluptuosas y libres, sobre el mundo oriental, eternizado por su sonrisa.\n" +
                "\n" +
                "Yo os las entrego tales como son, en su frescor de carne y de rosa. Sólo existe un método honrado y lógico de traducción: la «literalidad», una literalidad impersonal, apenas atenuada por un leve parpadeo y una ligera sonrisa del traductor. Ella crea, sugestiva, la más grande potencia literaria. Ella produce el placer de la evocación. Ella es la garantía de la verdad. Ella es firme e inmutable, en su desnudez de piedra. Ella cautiva el aroma primitivo y lo cristaliza. Ella separa y desata... Ella fija." +
                "Al venir al mundo fueron delicadamente mecidas por las manos de la lustral Doniazada, su buena tía, que grabó sus nombres sobre hojas de oro coloreadas de húmedas pedrerías y las cuidó bajo el terciopelo de sus pupilas hasta la adolescencia dura, para esparcirlas después, voluptuosas y libres, sobre el mundo oriental, eternizado por su sonrisa.\n" +
                "\n" +
                "Yo os las entrego tales como son, en su frescor de carne y de rosa. Sólo existe un método honrado y lógico de traducción: la «literalidad», una literalidad impersonal, apenas atenuada por un leve parpadeo y una ligera sonrisa del traductor. Ella crea, sugestiva, la más grande potencia literaria. Ella produce el placer de la evocación. Ella es la garantía de la verdad. Ella es firme e inmutable, en su desnudez de piedra. Ella cautiva el aroma primitivo y lo cristaliza. Ella separa y desata... Ella fija."
        bindDetailInfo()
    }

    private fun bindDetailInfo() {
        //binding.tvDetailInfo.text = "Original language: ${binding.movie?.original_language}"
        binding.tvDetailInfo.text = buildSpannedString {
            bold { append("Original language: ") }
            appendLine(binding.movie?.original_language)

            bold { append("Original title: ") }
            appendLine(binding.movie?.original_title)

            bold { append("Release date: ") }
            appendLine(binding.movie?.release_date)

            bold { append("Popularity: ") }
            appendLine(binding.movie?.popularity.toString())

            bold { append("Vote average: ") }
            appendLine(binding.movie?.vote_average.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}