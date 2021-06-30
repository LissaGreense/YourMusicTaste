package com.example.yourmusictaste.activities.api

import com.squareup.moshi.Json

data class UserInfo(
    @Json(name = "user")
    val user: User
)

data class User(
    @Json(name = "age")
    val age: String,
    @Json(name = "bootstrap")
    val bootstrap: String,
    @Json(name = "country")
    val country: String,
    @Json(name = "gender")
    val gender: String,
    @Json(name = "image")
    val image: List<Image>,
    @Json(name = "name")
    val name: String,
    @Json(name = "playcount")
    val playcount: String,
    @Json(name = "playlists")
    val playlists: String,
    @Json(name = "realname")
    val realname: String,
    @Json(name = "registered")
    val registered: Registered,
    @Json(name = "subscriber")
    val subscriber: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "url")
    val url: String
)

data class Image(
    @Json(name = "size")
    val size: String,
    @field:Json(name = "#text")
    val text: String
)

data class Registered(
    @field:Json(name = "#text")
    val text: Int,
    @Json(name = "unixtime")
    val unixtime: String
)
data class UserFriends(
    @Json(name = "friends")
    val friends: Friends
)

data class Friends(
    @field:Json(name = "@attr")
    val attr: Attr,
    @Json(name = "user")
    val user: List<User>
)

data class Attr(
    @Json(name = "page")
    val page: String,
    @Json(name = "perPage")
    val perPage: String,
    @Json(name = "total")
    val total: String,
    @Json(name = "totalPages")
    val totalPages: String,
    @Json(name = "user")
    val user: String
)
data class RecentTracks(
    @Json(name = "recenttracks")
    val recenttracks: RecenttracksX
)

data class RecenttracksX(
    @field:Json(name = "@attr")
    val attr: Attr,
    @Json(name = "track")
    val track: List<Track>
)


data class Track(
    @Json(name = "album")
    val album: Album,
    @Json(name = "artist")
    val artist: Artist,
    @field:Json(name = "@attr")
    val attr: AttrX,
    @Json(name = "date")
    val date: Date,
    @Json(name = "image")
    val image: List<Image>,
    @Json(name = "mbid")
    val mbid: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "streamable")
    val streamable: String,
    @Json(name = "url")
    val url: String
)

data class Album(
    @Json(name = "mbid")
    val mbid: String,
    @field:Json(name = "#text")
    val text: String
)

data class Artist(
    @Json(name = "mbid")
    val mbid: String,
    @field:Json(name = "#text")
    val text: String
)

data class AttrX(
    @Json(name = "nowplaying")
    val nowplaying: String
)

data class Date(
    @field:Json(name = "#text")
    val text: String,
    @Json(name = "uts")
    val uts: String
)

data class SimilarSongs(
    @Json(name = "similartracks")
    val similartracks: SimilarTracks
)

data class SimilarTracks(
    @field:Json(name = "@attr")
    val attr: SimilarAttr,
    @Json(name = "track")
    val track: List<SimilarTrack>
)

data class SimilarAttr(
    @Json(name = "artist")
    val artist: String
)

data class SimilarTrack(
    @Json(name = "artist")
    val artist: SimilarArtist,
    @Json(name = "duration")
    val duration: Int,
    @Json(name = "image")
    val image: List<Image>,
    @Json(name = "match")
    val match: Double,
    @Json(name = "mbid")
    val mbid: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "playcount")
    val playcount: Int,
    @Json(name = "streamable")
    val streamable: Streamable,
    @Json(name = "url")
    val url: String
)

data class SimilarArtist(
    @Json(name = "mbid")
    val mbid: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)

data class Streamable(
    @Json(name = "fulltrack")
    val fulltrack: String,
    @field:Json(name = "#text")
    val text: String
)
data class TrackInfo(
    @Json(name = "track")
    val track: TrackFromInfo
)

data class TrackFromInfo(
    @Json(name = "album")
    val album: AlbumFromInfo,
    @Json(name = "artist")
    val artist: ArtistFromInfo,
    @Json(name = "duration")
    val duration: String,
    @Json(name = "listeners")
    val listeners: String,
    @Json(name = "mbid")
    val mbid: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "playcount")
    val playcount: String,
    @Json(name = "streamable")
    val streamable: Streamable,
    @Json(name = "toptags")
    val toptags: Toptags,
    @Json(name = "url")
    val url: String,
    @Json(name = "wiki")
    val wiki: Wiki
)

data class AlbumFromInfo(
    @Json(name = "artist")
    val artist: String,
    @field:Json(name = "@attr")
    val attr: Attr,
    @Json(name = "image")
    val image: List<Image>,
    @Json(name = "mbid")
    val mbid: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "url")
    val url: String
)

data class ArtistFromInfo(
    @Json(name = "mbid")
    val mbid: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)

data class Toptags(
    @Json(name = "tag")
    val tag: List<Tag>
)

data class Wiki(
    @Json(name = "content")
    val content: String,
    @Json(name = "published")
    val published: String,
    @Json(name = "summary")
    val summary: String
)


data class Tag(
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)

data class RankArtist(
    @Json(name = "topartists")
    val topartists: Topartists
)

data class Topartists(
    @Json(name = "artist")
    val artist: List<FromRankArtist>,
    @field:Json(name = "@attr")
    val attr: AttrX
)

data class FromRankArtist(
    @field:Json(name = "@attr")
    val attr: RankAttr,
    @Json(name = "image")
    val image: List<Image>,
    @Json(name = "mbid")
    val mbid: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "playcount")
    val playcount: String,
    @Json(name = "streamable")
    val streamable: String,
    @Json(name = "url")
    val url: String
)

data class RankAttr(
    @Json(name = "rank")
    val rank: String
)
