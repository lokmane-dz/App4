package me.lokmvne.app4.presentation

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.lokmvne.app4.AlarmReceiver
import javax.inject.Inject

@HiltViewModel
class AlarmManagerViewModel @Inject constructor() : ViewModel() {
    private var alarmMgr: AlarmManager? = null
    private lateinit var alarmIntent: PendingIntent

    fun setAlarm(context: Context) {
        alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(context, AlarmReceiver::class.java).let { intent ->
            intent.action = "AlarmApp4"
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        }
        viewModelScope.launch {
            alarmMgr?.setExactAndAllowWhileIdle(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 15 * 1000,
                alarmIntent
            )
        }
    }
}