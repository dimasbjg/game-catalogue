package com.dimdimbjg.gamecatalogue.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dimdimbjg.core.domain.model.Games
import com.dimdimbjg.gamecatalogue.databinding.ItemLayoutBinding
import com.dimdimbjg.gamecatalogue.ui.detail.DetailActivity
import com.dimdimbjg.gamecatalogue.ui.detail.DetailActivity.Companion.GAME_ID

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var listGame = listOf<Games>()

    fun setGames(games: List<Games>?) {
        games?.let {
            this.listGame = it
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(game: Games) {
            with(binding) {
                tvTitle.text = game.name

                Glide.with(itemView.context)
                    .load(game.backgroundImage)
                    .into(binding.imgGame)

                itemView.setOnClickListener {
                    val intent = Intent(it.context, DetailActivity::class.java)
                    intent.putExtra(GAME_ID, game.id)
                    it.context.startActivity(intent)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listGame[position])
    }

    override fun getItemCount(): Int = listGame.size
}