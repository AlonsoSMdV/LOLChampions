package com.turing.alan.cpifp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.turing.alan.cpifp.R
import com.turing.alan.cpifp.data.Champion
import com.turing.alan.cpifp.data.ChampionsRepository
import com.turing.alan.cpifp.data.InMemoryChampionsRepository
import com.turing.alan.cpifp.databinding.ChampionListFragmentBinding


class FragmentListChampion : Fragment() {
    private val repository: ChampionsRepository = InMemoryChampionsRepository.getInstance()
    private lateinit var binding: ChampionListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ChampionListFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ChampionAdapter(::toItemDetail)
        val reciclerView = binding.championsList
        reciclerView.adapter = adapter
        (reciclerView.adapter as ChampionAdapter).submitList(repository.getChampions())
    }

    override fun onResume() {
        super.onResume()
        val reciclerView = binding.championsList
        (reciclerView.adapter as ChampionAdapter).submitList(repository.getChampions())
    }

    private fun toItemDetail(champion: Champion) {
        val action = FragmentListChampionDirections
            .actionFragmentListChampionToFragmentDetailChampion(champion.id)
        findNavController().navigate(action)
    }
}