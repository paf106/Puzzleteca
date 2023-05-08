package com.pablo.puzzleteca.ui.view.components.common.puzzleAdapter


import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.pablo.puzzleteca.R
import com.pablo.puzzleteca.data.database.PuzzleDatabase
import com.pablo.puzzleteca.data.model.Puzzle
import com.pablo.puzzleteca.databinding.PuzzleItemBinding
import com.pablo.puzzleteca.ui.view.puzzles.PuzzleDetailActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class PuzzleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = PuzzleItemBinding.bind(view)
    val database = PuzzleDatabase.getInstance(view.context)

    fun render(puzzle: Puzzle, onClickListener: (Puzzle) -> Unit) {
        binding.txtName.text = puzzle.name
        binding.txtBrand.text = puzzle.brand
        binding.txtPieces.text = puzzle.pieces.toString()
        binding.txtLocation.text = puzzle.buyPlace
        binding.ivPhoto.setImageURI(Uri.fromFile(File(puzzle.photo)))

        binding.ivOptions.setOnClickListener { showPopupMenu(it, puzzle) }
        itemView.setOnClickListener { onClickListener(puzzle) }
    }

    private fun showPopupMenu(v: View, puzzle: Puzzle) {
        val popupMenu = PopupMenu(itemView.context, v)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {

                R.id.mnuDelete -> {
                    CoroutineScope(Dispatchers.IO).launch { database.getDao().delete(puzzle)
                    }; true

                }
                R.id.mnuEdit -> {
                    v.context.startActivity(
                        Intent(
                            itemView.context,
                            PuzzleDetailActivity::class.java
                        ).apply {
                            putExtra("puzzle", puzzle)
                        })
                    true
                }

                else -> true
            }
        }
        val popup = PopupMenu::class.java.getDeclaredField("mPopup")
        popup.isAccessible = true
        val menu = popup.get(popupMenu)
        menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
            .invoke(menu, true)
        popupMenu.show()
    }
}