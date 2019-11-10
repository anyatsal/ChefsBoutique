package by.anyatsal.chefsboutique.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
    var name: String? = null,
    var category: String,
    var description: String,
    var imageRes: String,
    var ingredients: String,
    var url: String? = null
) : Parcelable