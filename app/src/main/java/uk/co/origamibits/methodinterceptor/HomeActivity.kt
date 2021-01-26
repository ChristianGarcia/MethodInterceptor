package uk.co.origamibits.methodinterceptor

import android.view.View
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity(R.layout.activity_home) {

    fun buttonClicked(view: View) {
        val result = executeInTransactionWithResult { transaction, context ->
            // Method code start
            (0..100000).map {
                Item("${transaction.id} - $it")
            }
            // Method code end
        }
        // We can use Result (Error or Success) in here to decide further stuff
    }

    fun anotherMethod() {
        executeInTransaction { transaction, interceptContext ->
            // Actual method code
        }
    }

}

data class Item(val id: String)
