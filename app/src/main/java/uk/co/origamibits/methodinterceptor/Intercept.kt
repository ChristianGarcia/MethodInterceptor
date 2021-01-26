package uk.co.origamibits.methodinterceptor

import timber.log.Timber
import java.util.*
import kotlin.system.measureTimeMillis

fun <T> executeInTransaction(block: (Transaction, ExecutionContext) -> T): T {
    Timber.d("Before. Measuring method")
    val transaction = Transaction(
        id = UUID.randomUUID().toString()
    )
    val context = ExecutionContext(
        data = mapOf(
            "key0" to "value0",
            "key2" to "value1"
        )
    )
    var result: T?
    val executionTime = measureTimeMillis {
        result = block(transaction, context)
    }
    Timber.d("After. Time spent %s ms", executionTime)
    return result!!
}

data class Transaction(
    val id: String
)

data class ExecutionContext(
    val data: Map<String, Any>
)