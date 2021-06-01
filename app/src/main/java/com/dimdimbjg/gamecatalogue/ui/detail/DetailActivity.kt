package com.dimdimbjg.gamecatalogue.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dimdimbjg.core.data.source.Resource
import com.dimdimbjg.core.domain.model.Detail
import com.dimdimbjg.gamecatalogue.R
import com.dimdimbjg.gamecatalogue.databinding.ActivityDetailBinding
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val GAME_ID = "game_id"
    }

    private val viewModel: DetailViewModel by viewModel()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gameId = intent.getIntExtra(GAME_ID, 0)

        var isFavorite = false

        val data = viewModel.gameDetail(gameId)

        data.observe(this, { result ->
            when (result) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvError.visibility = View.GONE
                    hideContent(false)
                    populateContent(result.data as Detail)

                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    hideContent(true)
                }
                is Resource.Error -> {
                    hideContent(true)
                    binding.progressBar.visibility = View.GONE
                    showError()
                }
            }
        })

        viewModel.checkIsFavorite(gameId).observe(this, {
            isFavorite = it
            val icon = if(it) R.drawable.ic_favorite else R.drawable.ic_favorite_border
            binding.fab.setImageResource(icon)
        })

        binding.fab.setOnClickListener {
            if (isFavorite) {
                viewModel.removeGameFromFavorite(gameId)
                Toast.makeText(this, "Removed from favorite", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addGameToFavorites(gameId)
                Toast.makeText(this, "Added to favorite", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun populateContent(data: Detail) {
        with(binding) {
            gameTitle.text = data.name
            description.text = data.descriptionRaw
            (data.rating.toString() + "( ${data.ratingsCount.toString()} )").also { rating.text = it }
            "Release Data: ${data.released}".also { tvRelease.text = it }

            Glide.with(this@DetailActivity)
                .load(data.backgroundImage)
                .into(imgGame)

            Glide.with(this@DetailActivity)
                .load(data.backgroundImageAdditional)
                .into(imgPoster)
        }
    }

    private fun hideContent(state: Boolean) {
        if (state) {
            with(binding) {
                descriptionTitle.visibility = View.GONE
                imgGame.visibility = View.GONE
                fab.visibility = View.GONE
                descriptionTitle.visibility = View.GONE
                description.visibility = View.GONE
                star.visibility = View.GONE
                gameTitle.visibility = View.GONE
                imgPoster.visibility = View.GONE
                rating.visibility = View.GONE
                tvRelease.visibility = View.GONE
            }
        } else {
            with(binding) {
                descriptionTitle.visibility = View.VISIBLE
                imgGame.visibility = View.VISIBLE
                fab.visibility = View.VISIBLE
                descriptionTitle.visibility = View.VISIBLE
                description.visibility = View.VISIBLE
                star.visibility = View.VISIBLE
                gameTitle.visibility = View.VISIBLE
                imgPoster.visibility = View.VISIBLE
                rating.visibility = View.VISIBLE
                tvRelease.visibility = View.VISIBLE
            }
        }
    }

    private fun showError() {
        binding.tvError.visibility = View.VISIBLE
        binding.tvError.text = getString(R.string.error_message)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}