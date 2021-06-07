package com.dimdimbjg.gamecatalogue.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.dimdimbjg.core.data.source.Resource
import com.dimdimbjg.gamecatalogue.databinding.ActivityHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = Adapter()

        with(binding.rvGames) {
            layoutManager = GridLayoutManager(this@HomeActivity, 2)
            setHasFixedSize(true)
            this.adapter = adapter
        }

        viewModel.gameList.observe(this, { games ->
            when (games) {
                is Resource.Loading<*> -> binding.progressBar.visibility = View.VISIBLE
                is Resource.Success<*> -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvError.visibility = View.GONE
                    adapter.setGames(games.data)
                }
                is Resource.Error<*> -> {
                    binding.progressBar.visibility = View.GONE
                    games.message?.let { setErrorText(it) }
                }
            }
        })

        binding.fab.setOnClickListener {
            val uri = Uri.parse("gamecatalogue://favorite")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    private fun setErrorText(error: String) {
        with(binding.tvError) {
            visibility = View.VISIBLE
            text = error
        }
    }

}