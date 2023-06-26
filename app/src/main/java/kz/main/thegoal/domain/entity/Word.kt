package kz.main.thegoal.domain.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
@Entity
data class Word(
    @PrimaryKey
    var uuid: Int,
    var wordTitle: String = "",
    var wordText: String = "",
    var liked: Boolean = false
) : Parcelable
