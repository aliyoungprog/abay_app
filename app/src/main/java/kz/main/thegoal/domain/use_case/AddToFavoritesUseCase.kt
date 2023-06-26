package kz.main.thegoal.domain.use_case

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kz.main.thegoal.data.WordDao
import kz.main.thegoal.domain.entity.Word

class AddToFavoritesUseCase {

    suspend operator fun invoke(word: Word, wordDao: WordDao) = withContext(Dispatchers.IO) {
        wordDao.addToFavorites(word)
    }
}
