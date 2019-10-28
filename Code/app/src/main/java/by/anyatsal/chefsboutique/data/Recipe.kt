package by.anyatsal.chefsboutique.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
    @SerializedName("title")
    var name: String? = null,
    var category: String,
    @SerializedName("publisher")
    var description: String,
    @SerializedName("image_url")
    var imageRes: String,
    var ingredients: String,
    @SerializedName("source_url")
    var url: String? = null
): Parcelable