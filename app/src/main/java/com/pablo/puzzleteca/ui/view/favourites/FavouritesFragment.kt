package com.pablo.puzzleteca.ui.view.favourites

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pablo.puzzleteca.data.database.PuzzleDatabase
import com.pablo.puzzleteca.data.model.Puzzle
import com.pablo.puzzleteca.databinding.FragmentFavouritesBinding
import com.pablo.puzzleteca.ui.view.puzzles.PuzzleDetailActivity
import com.pablo.puzzleteca.ui.view.components.common.puzzleAdapter.PuzzleAdapter
import com.pablo.puzzleteca.ui.viewmodel.FavouritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : Fragment() {
    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!
    private val favouritesViewModel: FavouritesViewModel by viewModels()
    private lateinit var database: PuzzleDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favouritesViewModel.onCreate()

        favouritesViewModel.favouritesPuzzles.observe(
            viewLifecycleOwner,
            Observer { initRecyclerView(it!!) })

        /*database = PuzzleDatabase.getInstance(requireContext())

        database.getDao().getFavourites().observe(viewLifecycleOwner, Observer {
            initRecyclerView(it)
        })*/
    }

    fun initRecyclerView(puzzleList: List<Puzzle>) {
        binding.rvFavourites.layoutManager = LinearLayoutManager(view?.context)
        binding.rvFavourites.adapter = PuzzleAdapter(puzzleList) { onItemSelected(it) }
    }

    fun onItemSelected(puzzle: Puzzle) {

        startActivity(Intent(view?.context, PuzzleDetailActivity::class.java).apply {
            putExtra("puzzle", puzzle)
        })

    }


}