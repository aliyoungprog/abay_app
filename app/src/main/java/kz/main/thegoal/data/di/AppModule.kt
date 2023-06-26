package kz.main.thegoal.data.di

import androidx.room.Room
import kz.main.thegoal.data.WordsDatabase
import kz.main.thegoal.domain.use_case.AddToFavoritesUseCase
import kz.main.thegoal.presentation.viewModels.WordViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        AddToFavoritesUseCase()
    }
    single {
        Room.databaseBuilder(
            androidApplication(), WordsDatabase::class.java, "words-db"
        ).fallbackToDestructiveMigration().build()
    }
    single {
        val db = get<WordsDatabase>()
        db.wordDao()
    }
    viewModel {
        WordViewModel(addToFavoritesUseCase = get(), wordDao = get(), context = androidContext())
    }
}
