package uk.co.origamibits.methodinterceptor

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