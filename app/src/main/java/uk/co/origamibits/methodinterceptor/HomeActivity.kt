package uk.co.origamibits.methodinterceptor

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class HomeActivity : AppCompatActivity(R.layout.activity_home) {

    fun buttonClicked(view: View) {
        val result = intercept { transaction, context ->
            (0..100000).map {
                Item("${transaction.id} - $it")
            }
        }
        Timber.d("List size is %s", result.size)
    }

    fun anotherMethod() {
        intercept { transaction, interceptContext ->
            // Actual method code
        }
    }

}

data class Item(val id: String)
