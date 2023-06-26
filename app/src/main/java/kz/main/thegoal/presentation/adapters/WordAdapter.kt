package kz.main.thegoal.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.main.thegoal.R
import kz.main.thegoal.domain.entity.Word
import kz.main.thegoal.presentation.viewHolders.WordViewHolder

class WordAdapter(
    private val goals: List<Word>,
    private val onClick: (Word) -> Unit,
) :
    RecyclerView.Adapter<WordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.topic_recycler_view, parent, false)
        val viewHolder = WordViewHolder(view, onClick)
        return viewHolder
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.onBind(goals[position])
    }

    override fun getItemCount(): Int {
        return goals.size
    }
}
