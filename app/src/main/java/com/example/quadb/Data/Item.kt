package com.example.quadb.Data

data class Item(
    val score: Double,
    val show: Show
)

data class Show(
    val id: Int,
    val url: String,
    val name: String,
    val type: String,
    val language: String,
    val genres: List<String>,
    val status: String,
    val runtime: Int?,
    val averageRuntime: Int?,
    val premiered: String?,
    val ended: String?,
    val officialSite: String?,
    val schedule: Schedule,
    val rating: Rating,
    val weight: Int,
    val network: Network?,
    val webChannel: Any?,
    val dvdCountry: Any?,
    val externals: Externals,
    val image: Image?,
    val summary: String?,
    val updated: Long,
    val _links: Links
)

data class Schedule(
    val time: String,
    val days: List<String>
)

data class Rating(
    val average: Double?
)

data class Network(
    val id: Int,
    val name: String,
    val country: Country?,
    val officialSite: String?
)

data class Country(
    val name: String,
    val code: String,
    val timezone: String
)

data class Externals(
    val tvrage: Any?,
    val thetvdb: Int?,
    val imdb: String?
)

data class Image(
    val medium: String,
    val original: String
)

data class Links(
    val self: Link,
    val previousepisode: EpisodeLink?,
    val nextepisode: EpisodeLink?
)

data class Link(
    val href: String
)

data class EpisodeLink(
    val href: String,
    val name: String?
)
