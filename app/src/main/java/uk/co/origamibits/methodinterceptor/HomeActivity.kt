package uk.co.origamibits.methodinterceptor

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber
import kotlin.system.measureTimeMillis

class HomeActivity : AppCompatActivity(R.layout.activity_home) {

    fun buttonClicked(view: View) {
        intercept {
            (0..1000000).map {
                Item("$it")
            }
        }
    }

    fun anotherMethod() {
        intercept {
            // Actual method code
        }
    }

    private fun intercept(block: () -> Unit) {
        Timber.d("Before. Measuring method")
        val executionTime = measureTimeMillis {
            block()
        }
        Timber.d("After. Time spent %s ms", executionTime)
    }

}

data class Item(val id: String)
