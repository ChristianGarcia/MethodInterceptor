package uk.co.origamibits.methodinterceptor

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber
import java.util.*
import kotlin.system.measureTimeMillis

class HomeActivity : AppCompatActivity(R.layout.activity_home) {

    fun buttonClicked(view: View) {
        val result = intercept { transactionId ->
            (0..100000).map {
                Item("$transactionId - $it")
            }
        }
        Timber.d("List size is %s", result.size)
    }

    fun anotherMethod() {
        intercept {
            // Actual method code
        }
    }

}

data class Item(val id: String)

private fun <T> intercept(block: (String) -> T): T {
    Timber.d("Before. Measuring method")
    val transactionId = UUID.randomUUID().toString()
    var result: T?
    val executionTime = measureTimeMillis {
        result = block(transactionId)
    }
    Timber.d("After. Time spent %s ms", executionTime)
    return result!!
}
