package by.anyatsal.chefsboutique.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Recipe(
    @PrimaryKey
    val name: String,
    val category: String,
    val description: String,
    val imageRes: String,
    val ingredients: String
) : Parcelable