package kz.main.thegoal.presentation.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kz.main.thegoal.data.WordDao
import kz.main.thegoal.domain.entity.Word
import kz.main.thegoal.domain.use_case.AddToFavoritesUseCase
import kz.main.thegoal.presentation.WordsProvider
import java.util.*

class WordViewModel(
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val wordDao: WordDao,
    private val context: Context
) : ViewModel() {

    private val _favorites = MutableLiveData<List<Word>>()
    val favorites: LiveData<List<Word>> = _favorites

    private val listOfWords by lazy {
        WordsProvider(context).getWords()
    }

    fun addToFavorite(word: Word) = viewModelScope.launch {
        withContext(Dispatchers.IO) { addToFavoritesUseCase(word, wordDao) }
    }

    fun getAllFavorites() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val favoritesList = wordDao.getFavoritesWords()
            sortFavorites(favoritesList)
            _favorites.postValue(listOfWords)
        }
    }

    fun removeFromFavorite(word: Word) = viewModelScope.launch {
        withContext(Dispatchers.IO) { wordDao.deleteFromFavorites(word) }
    }

    private fun sortFavorites(list: List<Word>) {

        val cmp = object : Comparator<Word> {
            override fun compare(o1: Word?, o2: Word?): Int {
                if (o1?.liked == true) return -1
                if (o2?.liked == true) return 1
                return 0
            }
        }
        listOfWords.forEach { l ->
            list.forEach { r ->
                if (l.uuid == r.uuid) {
                    l.liked = true
                }
            }
        }
        listOfWords.let { Collections.sort(it, cmp) }
    }

    override fun onCleared() {
        super.onCleared()
        listOfWords.clear()
    }
}
