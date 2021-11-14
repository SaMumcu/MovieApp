package com.samumcu.movieapp.data.retrofit.response

import com.google.gson.annotations.SerializedName


data class MovieDetailsResponse(
        @SerializedName("genres") val genres: List<MovieGenreResponse>?,
        @SerializedName("id") val id: Int?,
        @SerializedName("overview") val overview: String?,
        @SerializedName("popularity") val popularity: Double?,
        @SerializedName("poster_path") val posterPath: String,
        @SerializedName("backdrop_path") val backdropPath: String,
        @SerializedName("release_date") val releaseDate: String?,
        @SerializedName("runtime") val runtime: Int?,
        @SerializedName("title") val title: String?,
        @SerializedName("vote_average") val voteAverage: Float?,
)

data class MovieGenreResponse(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String
)