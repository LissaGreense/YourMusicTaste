package com.example.yourmusictaste.activities.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFmRequests {
    @GET("/2.0/?method=user.getinfo&format=json")
    fun getInfo(@Query("user") username: String): Call<UserInfo>

    @GET("/2.0/?method=user.getfriends&format=json")
    fun getFriends(@Query("user") username: String): Call<UserFriends>

    @GET("/2.0/?method=user.getrecenttracks&format=json")
    fun getRecentTracks(
        @Query("user") username: String,
        @Query("limit") limit: Int
    ): Call<RecentTracks>

    @GET("/2.0/?method=track.getInfo&format=json")
    fun getTrackInfo(
        @Query("artist") artist: String,
        @Query("track") track: String
    ): Call<TrackInfo>

    @GET("/2.0/?method=track.getsimilar&format=json")
    fun getSimilarTracks(
        @Query("artist") artist: String,
        @Query("track") track: String,
        @Query("limit") limit: Int
    ): Call<SimilarSongs>

    @GET("/2.0/?method=user.gettopartists&format=json")
    fun getTopArtists(
        @Query("user") username: String,
        @Query("limit") limit: Int
    ): Call<RankArtist>

}