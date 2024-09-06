package me.lokmvne.app4

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlarmReceiver() : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "AlarmApp4") {
            Toast.makeText(context, "Alarm received!(if block)", Toast.LENGTH_SHORT).show()
        }
        Toast.makeText(context, "Alarm received!", Toast.LENGTH_SHORT).show()
    }
}
