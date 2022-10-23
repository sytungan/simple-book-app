package at.ac.fhcampuswien.bookapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val author: String,
    val date: Int,
    val iBSN: String,
)
