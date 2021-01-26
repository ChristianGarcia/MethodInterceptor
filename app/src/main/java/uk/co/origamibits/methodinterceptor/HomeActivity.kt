package uk.co.origamibits.methodinterceptor

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class HomeActivity : AppCompatActivity(R.layout.activity_home) {

    fun buttonClicked(view: View) {
        val result = executeTransaction { transaction, context ->
            // Method code start
            (0..100000).map {
                Item("${transaction.id} - $it")
            }
            // Method code end
        }
        Timber.d("List size is %s", result.size)
    }

    fun anotherMethod() {
        executeTransaction { transaction, interceptContext ->
            // Actual method code
        }
    }

}

data class Item(val id: String)
