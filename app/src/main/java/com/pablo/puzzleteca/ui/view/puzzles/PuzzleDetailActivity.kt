package com.pablo.puzzleteca.ui.view.puzzles

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.pablo.puzzleteca.R
import com.pablo.puzzleteca.data.model.Puzzle
import com.pablo.puzzleteca.databinding.ActivityPuzzleDetailBinding
import com.pablo.puzzleteca.ui.viewmodel.PuzzleDetailViewModel
import com.pablo.puzzleteca.ui.viewmodel.PuzzleViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class PuzzleDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPuzzleDetailBinding
    private lateinit var puzzle: Puzzle
    private val puzzleDetailViewModel: PuzzleDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPuzzleDetailBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val bundle = intent.extras
        puzzle = bundle?.getParcelable("puzzle")!!

        setupUI()
        puzzleDetailViewModel.isFavourite.observe(this, Observer {
            if (it){
                binding.fabFavourite.setImageResource(R.drawable.ic_favorite)
                Log.i("fav", "favourite")
            }else{
                binding.fabFavourite.setImageResource(R.drawable.ic_not_favorite)
                Log.i("fav", "not fav")
            }
        })
    }

    fun setupUI() {
        binding.fabFavourite.setOnClickListener { puzzleDetailViewModel.setFavourite(puzzle) }
        // Cambiar imagen del icono si el puzzle está añadido a favoritos
        if (puzzle.isFavourite) binding.fabFavourite.setImageResource(R.drawable.ic_favorite)
        else binding.fabFavourite.setImageResource(R.drawable.ic_not_favorite)

        binding.ctlToolbar.title = puzzle.name
        binding.tvInfoBrand.text = puzzle.brand
        binding.txtBoughtDay.text = puzzle.buyDay
        binding.txtBoughtLocation.text = puzzle.buyPlace
        binding.tvInfoCollection.text = puzzle.collection
        binding.txtComments.text = puzzle.comment
        binding.txtDoneIn.text = puzzle.doneTime
        binding.tvInfoPieces.text = puzzle.pieces.toString()
        binding.tvInfoReferenceNumber.text = puzzle.referenceNumber.toString()
        binding.ivPuzzle.setImageURI(Uri.fromFile(File(puzzle.photo)))
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.popup_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.mnuEdit -> true
            R.id.mnuDelete -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}