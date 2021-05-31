package com.dimdimbjg.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dimdimbjg.favorite.databinding.ActivityFavoritesBinding
import com.dimdimbjg.favorite.di.favoriteModule
import org.koin.core.context.loadKoinModules

class FavoritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)
    }
}