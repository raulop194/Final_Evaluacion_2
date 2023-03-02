package com.example.finalevaluacion2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.finalevaluacion2.databinding.ActivityMovieTitleBinding
import com.example.finalevaluacion2.models.Movie
import com.google.gson.Gson

class MovieTitleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieTitleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieTitleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextButton.setOnClickListener {
            val movieTitle = binding.titleField.text.toString()
            val duration = try {
                binding.durationField.text.toString().toInt()
            } catch (e: NumberFormatException) { 0 }

            if (movieTitle.isEmpty() || movieTitle.isBlank()){
                Toast.makeText(this, "Debe insertar un titulo.", Toast.LENGTH_LONG)
                    .show()

                return@setOnClickListener
            }

            if (duration <= 0){
                Toast.makeText(
                    this,
                    "Debe insertar una duración valida.",
                    Toast.LENGTH_LONG
                ).show()

                return@setOnClickListener
            }

            val movie = Movie(movieTitle, duration, "NOT YET", -1, false)

            startActivity(
                Intent(this, MovieDetailsActivity::class.java)
                    .putExtra("MOVIE_OBJ", Gson().toJson(movie))
            )
        }

    }
}