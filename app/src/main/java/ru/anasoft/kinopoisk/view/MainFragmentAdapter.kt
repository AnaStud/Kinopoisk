package ru.anasoft.kinopoisk.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.anasoft.kinopoisk.R
import ru.anasoft.kinopoisk.model.Film

class MainFragmentAdapter(private var onItemViewClickListener: MainFragment.OnItemViewClickListener?)
    : RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {

    private var listOfFilms: List<Film> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setListOfFilms(listOfFilms: List<Film>) {
        this.listOfFilms = listOfFilms
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_main_item, parent, false) as View
    )

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(listOfFilms[position])
    }

    override fun getItemCount() = listOfFilms.size

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(film: Film) {
            itemView.apply {
                findViewById<TextView>(R.id.mainFragmentRecyclerItemTitle).text = film.title
                setOnClickListener {
                    onItemViewClickListener?.onItemViewClick(film)
                }
            }
        }
    }

    fun removeListener() {
        onItemViewClickListener = null
    }

}