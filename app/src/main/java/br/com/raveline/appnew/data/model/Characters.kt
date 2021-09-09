package br.com.raveline.appnew.data.model


import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("results")
    val results: List<Character>?
)