package dev.kowalski.independentmvvm

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()

        Single.zip(
            checkConnection(),
            Single.timer(1, TimeUnit.SECONDS),
            BiFunction<Boolean, Long, Unit> { hasConnection, _ ->
                MainActivity.start(this, hasConnection)
            }
        ).subscribe()
    }

    private fun checkConnection(): Single<Boolean> {
        return Single.fromCallable {
            try {
                val socket = Socket()
                socket.connect(InetSocketAddress("kowalski.dev", 80), 1500)
                socket.close()
                true
            } catch (e: IOException) {
                false
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}