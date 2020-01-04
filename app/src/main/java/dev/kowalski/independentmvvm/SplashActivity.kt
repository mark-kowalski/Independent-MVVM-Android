package dev.kowalski.independentmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            MainActivity.start(this)
        }, 2000)
    }

}