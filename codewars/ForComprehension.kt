import java.util.Optional

fun <T> `for`(lambda: suspend SequenceScope<Optional<T>>.() -> Unit): Optional<T> = sequence { lambda() }.first()

suspend fun <T> SequenceScope<Optional<T>>.yield(value: T) = yield(Optional.of(value))

suspend fun <T, U> SequenceScope<Optional<T>>.bind(value: Optional<U>): U {
    if (value.isEmpty) yield(Optional.empty())
    return value.get()
}
