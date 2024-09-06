package me.lokmvne.app4.model

data class VideoItem(
    val title: String,
    val videoUrl: String,
)

val videoItems = listOf(
    VideoItem(
        title = "Aljazeera Mubacher",
        videoUrl = "https://live-hls-web-ajm.getaj.net/AJM/index.m3u8"
    ),

    VideoItem(
        title = "Nat Geo Wild",
        videoUrl = "http://stream02.vnet.am/NatGeoWild/mono.m3u8"
    ),
    VideoItem(
        title = "Big Buck Bunny",
        videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
    ),

    )