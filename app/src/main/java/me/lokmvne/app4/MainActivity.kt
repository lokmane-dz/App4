package me.lokmvne.app4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.compose.APP4Theme
import dagger.hilt.android.AndroidEntryPoint
import me.lokmvne.app4.presentation.ExoplayerScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            APP4Theme {
                //AlarmManagerScreen(context = this)
                ExoplayerScreen()
            }
        }
    }
}

