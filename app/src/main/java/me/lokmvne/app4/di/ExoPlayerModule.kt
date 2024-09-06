package me.lokmvne.app4.di

import android.annotation.SuppressLint
import android.app.Application
import android.os.Looper
import androidx.annotation.OptIn
import androidx.media3.common.C.WAKE_MODE_NETWORK
import androidx.media3.common.util.Clock
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewScoped

@OptIn(UnstableApi::class)
@Module
@InstallIn(ViewModelComponent::class)
object ExoPlayerModule {
    @SuppressLint("VisibleForTests")
    @Provides
    @ViewScoped
    fun provideExoPlayer(app:Application): ExoPlayer {
        val trackSelector = DefaultTrackSelector(app)
        val loadControl = DefaultLoadControl()
        return ExoPlayer.Builder(app)
            .setTrackSelector(trackSelector)
            .setClock(Clock.DEFAULT)
            .setLooper(Looper.getMainLooper())
            .setWakeMode(WAKE_MODE_NETWORK)
            .setLoadControl(loadControl)
            .build()
    }
}