package uk.co.origamibits.methodinterceptor

import timber.log.Timber
import java.util.*
import kotlin.system.measureTimeMillis

fun <T> executeInTransactionWithResult(block: (Transaction, ExecutionContext) -> T): Result<T> {
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
    val result: Result<T>
    val executionTime = measureTimeMillis {
        result = try {
            val resultValue = block(transaction, context)
            // Can use resultValue for whatever we want here
            Timber.d("Transaction %s successful", transaction.id)
            Result.Success(resultValue)
        } catch (t: Throwable) {
            Timber.w(t, "Transaction %s failed", transaction.id)
            Result.Error(t)
        }
    }
    Timber.d("After. Time spent %s ms", executionTime)
    return result
}

fun executeInTransaction(block: (Transaction, ExecutionContext) -> Unit) {
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
    val executionTime = measureTimeMillis {
        try {
            block(transaction, context)
            Timber.d("Transaction %s successful", transaction.id)
        } catch (t: Throwable) {
            Timber.w(t, "Transaction %s failed", transaction.id)
            Result.Error(t)
        }
    }
    Timber.d("After. Time spent %s ms", executionTime)
}

data class Transaction(
    val id: String
)

data class ExecutionContext(
    val data: Map<String, Any>
)

sealed class Result<out Any> {
    data class Error(val t: Throwable) : Result<Nothing>()
    data class Success<T>(val value: T) : Result<T>()
}