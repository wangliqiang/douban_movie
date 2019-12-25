package com.app.douban_movie.data.model
import com.google.gson.annotations.SerializedName


data class Theaters(
    @SerializedName("count")
    val count: Int,
    @SerializedName("start")
    val start: Int,
    @SerializedName("subjects")
    val subjects: List<Subject>,
    @SerializedName("title")
    val title: String,
    @SerializedName("total")
    val total: Int
)

data class Subject(
    @SerializedName("alt")
    val alt: String,
    @SerializedName("casts")
    val casts: List<Cast>,
    @SerializedName("collect_count")
    val collectCount: Int,
    @SerializedName("directors")
    val directors: List<Director>,
    @SerializedName("durations")
    val durations: List<String>,
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("has_video")
    val hasVideo: Boolean,
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: Images,
    @SerializedName("mainland_pubdate")
    val mainlandPubdate: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("pubdates")
    val pubdates: List<String>,
    @SerializedName("rating")
    val rating: Rating,
    @SerializedName("subtype")
    val subtype: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: String
)

data class Cast(
    @SerializedName("alt")
    val alt: String,
    @SerializedName("avatars")
    val avatars: Avatars,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("name_en")
    val nameEn: String
)

data class Avatars(
    @SerializedName("large")
    val large: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("small")
    val small: String
)

data class Director(
    @SerializedName("alt")
    val alt: String,
    @SerializedName("avatars")
    val avatars: AvatarsX,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("name_en")
    val nameEn: String
)

data class AvatarsX(
    @SerializedName("large")
    val large: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("small")
    val small: String
)

data class Images(
    @SerializedName("large")
    val large: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("small")
    val small: String
)

data class Rating(
    @SerializedName("average")
    val average: Double,
    @SerializedName("details")
    val details: Details,
    @SerializedName("max")
    val max: Int,
    @SerializedName("min")
    val min: Int,
    @SerializedName("stars")
    val stars: String
)

data class Details(
    @SerializedName("1")
    val x1: Double,
    @SerializedName("2")
    val x2: Double,
    @SerializedName("3")
    val x3: Double,
    @SerializedName("4")
    val x4: Double,
    @SerializedName("5")
    val x5: Double
)