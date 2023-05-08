package com.pablo.puzzleteca.ui.view.components.common.puzzleAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pablo.puzzleteca.R
import com.pablo.puzzleteca.data.model.Puzzle


class PuzzleAdapter(
    private val puzzleList: List<Puzzle>,
    private val onClickListener: (Puzzle) -> Unit
) :
    RecyclerView.Adapter<PuzzleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PuzzleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PuzzleViewHolder(layoutInflater.inflate(R.layout.puzzle_item, parent, false))
    }

    override fun onBindViewHolder(holder: PuzzleViewHolder, position: Int) {
        val item = puzzleList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = puzzleList.size
}