package com.example.experimentapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ReceiverTestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ReceiverTestView()
            }
        }
    }


    @Composable
    fun ReceiverTestView() {

        var airplaneModeStatus by remember {
            mutableStateOf("")
        }

        var airplaneModeStatusReceiver = remember {
            object : BroadcastReceiver() {
                override fun onReceive(p0: Context?, p1: Intent?) {
                    Log.d("Farman","Receiver Called")
                    val airplaneMode = intent?.getBooleanExtra("state", false) ?: false
                    Log.d("Farman","Receiver Called $airplaneMode")
                    airplaneModeStatus =
                        if (airplaneMode) "Airplane Mode is On" else "Airplane mode is off"
                }

            }
        }

        DisposableEffect(key1 = true) {
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).apply {
                registerReceiver(airplaneModeStatusReceiver,this)
            }
            onDispose {
                Log.d("Farman","Receiver unregistered")
                unregisterReceiver(airplaneModeStatusReceiver)
            }
        }

        var timerStartStop by remember { mutableStateOf(false) }
        val context = LocalContext.current

        val timer by produceState(initialValue = 10, timerStartStop) {
            val x = (1..10).random()
            var job: Job? = null
            Toast.makeText(context, "Start $x", LENGTH_SHORT).show()
            if (timerStartStop) {
                // Use MainScope to ensure awaitDispose is triggered
                job = MainScope().launch {
                    while (true) {
                        delay(1000)
                        value++
                    }
                }
            }

            awaitDispose {
                Toast.makeText(context, "Done $x", LENGTH_SHORT).show()
                job?.cancel()
            }
        }

       /* Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = airplaneModeStatus, color = Color.Black, fontSize = 26.sp)
        }*/
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Time $timer")
                Button(onClick = {
                    timerStartStop = !timerStartStop
                }) {
                    Text(if (timerStartStop) "Stop" else "Start")
                }
            }
        }
    }
}