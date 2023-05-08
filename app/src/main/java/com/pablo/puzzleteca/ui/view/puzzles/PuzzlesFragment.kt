package com.pablo.puzzleteca.ui.view.puzzles

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import androidx.recyclerview.widget.LinearLayoutManager
import com.pablo.puzzleteca.data.database.PuzzleDatabase
import com.pablo.puzzleteca.data.model.Puzzle
import com.pablo.puzzleteca.databinding.FragmentPuzzlesBinding
import com.pablo.puzzleteca.ui.view.AddEditPuzzleActivity
import com.pablo.puzzleteca.ui.view.components.common.puzzleAdapter.PuzzleAdapter
import com.pablo.puzzleteca.ui.viewmodel.PuzzleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PuzzlesFragment : Fragment() {
    private var _binding: FragmentPuzzlesBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: PuzzleDatabase
    private val puzzleViewModel: PuzzleViewModel by viewModels()
   /* private val list = listOf(
        Puzzle(
            1,
            "Nombre puzzle",
            "efgf",
            "Metallica",
            "fewfe",
            "coefme",
            "fesf",
            false,
            700,
            "gegeg",
            4363,
            "ravenburger",
            "Leganés",
            true
        ),
        Puzzle(
            1,
            "Cosas raras",
            "efgf",
            "Metallica",
            "fewfe",
            "coefme",
            "fesf",
            false,
            200,
            "gegeg",
            4363,
            "ravenburger",
            "Madrid",
            false
        ),
        Puzzle(
            1,
            "La vigen del pasico",
            "efgf",
            "Metallica",
            "fewfe",
            "coefme",
            "fesf",
            false,
            100,
            "gegeg",
            4363,
            "ravenburger",
            "Murcia",
            true
        ),
        Puzzle(
            1,
            "Nombre puzzle",
            "efgf",
            "Metallica",
            "fewfe",
            "coefme",
            "fesf",
            false,
            700,
            "gegeg",
            4363,
            "ravenburger",
            "Leganés",
            false
        ),
        Puzzle(
            1,
            "La vigen del pasico",
            "efgf",
            "Metallica",
            "fewfe",
            "coefme",
            "fesf",
            false,
            100,
            "gegeg",
            4363,
            "ravenburger",
            "Murcia",
            true
        ),
        Puzzle(
            1,
            "La vigen del pasico",
            "efgf",
            "Metallica",
            "fewfe",
            "coefme",
            "fesf",
            false,
            100,
            "gegeg",
            4363,
            "ravenburger",
            "Murcia",
            false
        ),
        Puzzle(
            1,
            "La vigen del pasico",
            "efgf",
            "Metallica",
            "fewfe",
            "coefme",
            "fesf",
            false,
            100,
            "gegeg",
            4363,
            "ravenburger",
            "Murcia",
            false
        ),
    )*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPuzzlesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        puzzleViewModel.getPuzzles()

        puzzleViewModel.puzzles.observe(viewLifecycleOwner, Observer { initRecyclerView(it!!) })

        /*database = PuzzleDatabase.getInstance(requireContext())

        database.getDao().getAll().observe(viewLifecycleOwner, Observer {
            initRecyclerView(list)
        })*/

        binding.fabAddPuzzle.setOnClickListener {
            startActivity(
                Intent(
                    context,
                    AddEditPuzzleActivity::class.java
                )
            )
        }
    }

    fun initRecyclerView(puzzleList: List<Puzzle>) {
        binding.rvPuzzles.layoutManager = LinearLayoutManager(view?.context)
        binding.rvPuzzles.adapter = PuzzleAdapter(puzzleList) { onItemSelected(it) }
    }

    fun onItemSelected(puzzle: Puzzle) {

        startActivity(Intent(view?.context, PuzzleDetailActivity::class.java).apply {
            putExtra("puzzle", puzzle)
        })

    }
}