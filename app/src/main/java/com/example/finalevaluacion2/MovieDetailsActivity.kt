package com.example.finalevaluacion2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.finalevaluacion2.databinding.ActivityMovieDetailsBinding
import com.example.finalevaluacion2.models.Movie
import com.google.gson.Gson

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener { finish() }
        binding.detailsNextButton.setOnClickListener {
            val directorName = binding.movieChiefField.text.toString()
            val releaseYear = try {
                    binding.releaseYearField.text.toString().toShort()
                } catch (e: NumberFormatException) { -1 }

            if (directorName.isEmpty() || directorName.isBlank()){
                Toast.makeText(
                    this,
                    "Debe insertar el nombre del director.",
                    Toast.LENGTH_LONG
                ).show()

                return@setOnClickListener
            }

            if (releaseYear <= 1920){
                Toast.makeText(
                    this,
                    "Debe insertar un año valido.",
                    Toast.LENGTH_LONG
                ).show()

                return@setOnClickListener
            }

            val movie = Gson()
                .fromJson(intent.getStringExtra("MOVIE_OBJ")?:"{}", Movie::class.java)

            movie.directorName = directorName
            movie.releaseYear = releaseYear
            movie.favorite = binding.favouriteSwitch.isChecked

            Log.d("DEBUG MOVIE", movie.toString())
            startActivity(
                Intent(this, MovieDisplayActivity::class.java)
                    .putExtra("MOVIE_OBJ", Gson().toJson(movie))
            )

            Toast.makeText(this, "Pelicula añadida.", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}