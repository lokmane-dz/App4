package me.lokmvne.app4.presentation

import android.annotation.SuppressLint
import android.net.Uri
import androidx.annotation.OptIn
import androidx.lifecycle.ViewModel
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.common.util.Util
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.dash.DashMediaSource
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.exoplayer.util.EventLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@SuppressLint("VisibleForTests")
@OptIn(UnstableApi::class)
@HiltViewModel
class ExoPlayerViewwModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var player: ExoPlayer

    fun playVideo(uri: Uri) {
        val dataSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()
        val type = Util.inferContentType(uri)
        player.setAudioAttributes(AudioAttributes.DEFAULT, true)
        player.addAnalyticsListener(EventLogger())
        val source = when (type) {
            C.CONTENT_TYPE_DASH -> DashMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(uri))

            C.CONTENT_TYPE_HLS -> HlsMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(uri))

            else -> ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(uri))
        }
        player.setMediaSource(source)
        player.prepare()
        player.play()
    }
}