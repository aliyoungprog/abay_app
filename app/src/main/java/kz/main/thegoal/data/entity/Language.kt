package kz.main.thegoal.data.entity

sealed class Language(lang: String) {
    data class Kaz(val lang: String) : Language(lang)
    data class Russ(val lang: String) : Language(lang)
    data class Eng(val lang: String) : Language(lang)
}
