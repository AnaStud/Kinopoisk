package ru.anasoft.kinopoisk.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ru.anasoft.kinopoisk.R
import ru.anasoft.kinopoisk.databinding.FragmentMainBinding
import ru.anasoft.kinopoisk.model.Film
import ru.anasoft.kinopoisk.viewmodel.AppState
import ru.anasoft.kinopoisk.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() {
            return _binding!!
        }

    private val adapter = MainFragmentAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(film: Film) {
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .add(R.id.container, DetailsFragment.newInstance(Bundle().apply {
                        putParcelable(DetailsFragment.BUNDLE_EXTRA, film) }))
                    .addToBackStack("")
                    .commit()
            }
        }
    })

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            mainFragmentRecyclerView.adapter = adapter
        }

        with(viewModel) {
            getLiveData().observe(viewLifecycleOwner, Observer<AppState> { renderData(it) })
            getListOfFilms()
        }
    }

    private fun renderData(appState:AppState) {
        with(binding) {
            when (appState) {
                is AppState.Loading -> {
                    loadingLayout.visibility = View.VISIBLE
                }
                is AppState.Success -> {
                    loadingLayout.visibility = View.GONE
                    adapter.setListOfFilms(appState.listOfFilms)
                }
                is AppState.Error -> {
                    loadingLayout.visibility = View.GONE
                    root.showSnackBar(
                        "Ошибка загрузки",
                        "Повторить",
                        { viewModel.getListOfFilms() })
                }
            }
        }
    }

    private fun View.showSnackBar(
        text: String,
        actionText: String,
        action: (View) -> Unit,
        length: Int = Snackbar.LENGTH_INDEFINITE) {

        Snackbar.make(this, text, length).setAction(actionText, action).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        adapter.removeListener()
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(film: Film)
    }

}