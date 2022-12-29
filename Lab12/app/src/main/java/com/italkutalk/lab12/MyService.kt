package com.italkutalk.lab12

import android.app.Service
import android.content.Intent
import android.os.IBinder
import kotlinx.coroutines.*

class MyService : Service() {
    override fun onCreate() {
        super.onCreate()
        val job: Job = GlobalScope.launch(Dispatchers.Main) {
            try {
                delay(5000)

                val intent = Intent(this@MyService,SecActivity::class.java)

                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                this@MyService.startActivity(intent)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        job.start()
    }

    override fun onStartCommand(intent: Intent,
                                flags: Int, startid: Int): Int {
        return START_NOT_STICKY //Service 終止後不再重啟
    }

    override fun onBind(intent: Intent): IBinder? = null
}