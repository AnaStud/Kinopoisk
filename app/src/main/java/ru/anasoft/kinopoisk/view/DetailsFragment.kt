package ru.anasoft.kinopoisk.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.anasoft.kinopoisk.databinding.FragmentDetailsBinding
import ru.anasoft.kinopoisk.model.Film

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    companion object {

        const val BUNDLE_EXTRA = "FILM_KEY"

        @JvmStatic
        fun newInstance(bundle:Bundle) = DetailsFragment().apply { arguments = bundle }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Film>(BUNDLE_EXTRA)?.let {
            setFilmData(it)
        }
    }

    private fun setFilmData(film: Film) {
        with(binding) {
            filmTitle.text = film.title
            filmYear.text = film.year.toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}