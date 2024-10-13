package com.turing.alan.cpifp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.turing.alan.cpifp.R
import com.turing.alan.cpifp.data.Champion
import coil.load
import com.turing.alan.cpifp.databinding.ChampionListBinding

class ChampionAdapter(private val toItemDetail:((Champion)-> Unit))
    : ListAdapter<Champion, ChampionAdapter.ChampionViewHolder>(ChampionDiffCallback) {

    inner class ChampionViewHolder(private val binding: ChampionListBinding)
        : ViewHolder(binding.root) {

        fun bind(champion: Champion) {
            binding.championTitle.text = champion.title
            binding.championName.text = champion.name
            binding.championImage.load(champion.imageUrl)
            binding.root.setOnClickListener{
                toItemDetail(champion)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionViewHolder {
        val binding = ChampionListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChampionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChampionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object ChampionDiffCallback: DiffUtil.ItemCallback<Champion>() {
        override fun areItemsTheSame(oldItem: Champion, newItem: Champion) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Champion, newItem: Champion) =
            oldItem.lore == newItem.lore &&
                    oldItem.name == newItem.name &&
                    oldItem.imageUrl == newItem.imageUrl &&
                    oldItem.title == newItem.title
    }
}