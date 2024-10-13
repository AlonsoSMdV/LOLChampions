package com.turing.alan.cpifp.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import androidx.navigation.fragment.navArgs
import com.turing.alan.cpifp.R
import com.turing.alan.cpifp.data.ChampionsRepository
import com.turing.alan.cpifp.data.InMemoryChampionsRepository
import com.turing.alan.cpifp.databinding.ChampionDetailFragmentBinding
import coil.load


class FragmentDetailChampion : Fragment() {
    private  val repository: ChampionsRepository = InMemoryChampionsRepository.getInstance()
    private val args: ChampionDetailFragmentArgs by navArgs()
    private lateinit var binding: ChampionDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ChampionDetailFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val championId = args.championId
        val champion = repository.readOne(championId)
        binding.championLore.text = champion.lore
        binding.championName.text = champion.name
        binding.championTitle.text = champion.title
        binding.championImage.load(champion.imageUrl)
    }

}