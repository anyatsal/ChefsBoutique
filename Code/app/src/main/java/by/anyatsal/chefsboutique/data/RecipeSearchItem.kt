package by.anyatsal.chefsboutique.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeSearchItem(
    @SerializedName("title")
    var name: String? = null,
    @SerializedName("image_url")
    var imageRes: String,
    @SerializedName("source_url")
    var url: String? = null
): Parcelable