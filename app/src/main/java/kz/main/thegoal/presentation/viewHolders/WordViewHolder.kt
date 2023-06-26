package kz.main.thegoal.presentation.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.main.thegoal.R
import kz.main.thegoal.domain.entity.Word

class WordViewHolder(
    view: View,
    private val onClick: (Word) -> Unit,
) : RecyclerView.ViewHolder(view) {

    fun onBind(word: Word) {
        val wordTitle = itemView.findViewById<TextView>(R.id.word_title)
        val isFavorite = itemView.findViewById<ImageView>(R.id.word_saved)
        wordTitle.text = word.wordTitle
        itemView.setOnClickListener {
            onClick(word)
        }
        if (word.liked) isFavorite.visibility = View.VISIBLE
        else isFavorite.visibility = View.GONE
    }
}
