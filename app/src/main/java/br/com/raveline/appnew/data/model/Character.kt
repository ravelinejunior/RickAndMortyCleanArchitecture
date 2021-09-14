package br.com.raveline.appnew.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Character(
    @SerializedName("created")
    val created: String?,
    @SerializedName("episode")
    val episode: @RawValue List<Any>?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("location")
    val location: @RawValue Location?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("origin")
    val origin: @RawValue Origin?,
    @SerializedName("species")
    val species: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?
) : Parcelable