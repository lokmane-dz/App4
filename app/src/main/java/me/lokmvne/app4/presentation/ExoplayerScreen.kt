package me.lokmvne.app4.presentation

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.ui.PlayerView
import me.lokmvne.app4.model.VideoItem
import me.lokmvne.app4.model.videoItems

@Composable
fun ExoplayerScreen() {
    val exoPlayerViewwModel = hiltViewModel<ExoPlayerViewwModel>()
    var lifecycle by remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 24.dp, bottom = 0.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(videoItems) {
            VideoItem(
                videoItem = it,
                exoPlayerViewwModel = exoPlayerViewwModel,
                lifecycle = lifecycle
            )
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp),
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

@Composable
fun VideoItem(
    videoItem: VideoItem,
    exoPlayerViewwModel: ExoPlayerViewwModel,
    lifecycle: Lifecycle.Event
) {
    val player = exoPlayerViewwModel.createExoPlayer(LocalContext.current)
    Spacer(modifier = Modifier.height(10.dp))
    AndroidView(
        factory = { context ->
            PlayerView(context).also {
                it.player = player
            }
        },
        update = {
            when (lifecycle) {
                Lifecycle.Event.ON_PAUSE -> {
                    it.onPause()
                    it.player?.pause()
                }

                Lifecycle.Event.ON_RESUME -> {
                    it.onResume()
                }

                else -> Unit
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .aspectRatio(16f / 9f),
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(text = videoItem.title, color = MaterialTheme.colorScheme.tertiary)
    exoPlayerViewwModel.playVideo(Uri.parse(videoItem.videoUrl), player)
}