package com.dimdimbjg.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.dimdimbjg.favorite.databinding.ActivityFavoritesBinding
import com.dimdimbjg.favorite.di.favoriteModule
import com.dimdimbjg.gamecatalogue.ui.home.Adapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        val adapter = Adapter()

        with(binding.rvGames) {
            layoutManager = GridLayoutManager(this@FavoritesActivity, 2)
            setHasFixedSize(true)
            this.adapter = adapter
        }

        viewModel.listFavorites.observe(this, { result ->
            adapter.setGames(result)
        })
    }
}