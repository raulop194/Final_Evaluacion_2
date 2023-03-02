package com.example.finalevaluacion2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.finalevaluacion2.databinding.ActivityMovieDisplayBinding
import com.example.finalevaluacion2.models.Movie
import com.google.gson.Gson

class MovieDisplayActivity : AppCompatActivity() {
    companion object {
        val movies = arrayListOf<Movie>()
    }

    lateinit var binding: ActivityMovieDisplayBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movies.add(
            Gson().fromJson(intent.getStringExtra("MOVIE_OBJ")?:"{}", Movie::class.java)
        )

        Log.d("MOVIES", movies.toString())

        binding.addFilmButton.setOnClickListener {
            startActivity(
                Intent(this, MovieTitleActivity::class.java)
            )
            finish()
        }


        renderFilms()

        binding.removeButton.setOnClickListener {
            val title = binding.displayTitleField.text.toString()
            if (title.isNotEmpty() || title.isNotBlank()){
                removeFilm(title)
            }
        }
    }

    private fun renderFilms() {
        var text = ""
        movies.forEach {
            text += "${it.title} - ${it.minDuration} min " +
                    "- Director: ${it.directorName} - ${it.releaseYear}" +
                    "- Favorito ${if(it.favorite) "Si" else "No"}\n"
        }

        binding.moviesList.text = text
    }

    private fun removeFilm(title: String) {
        movies.removeIf { it.title == title }
        renderFilms()
    }

}