package ch.yvu.rxpect.expect

import ch.yvu.rxpect.Expectation
import ch.yvu.rxpect.ExpectationWithLatch
import io.reactivex.Maybe
import org.mockito.stubbing.OngoingStubbing

fun <T : Any> OngoingStubbing<Maybe<T>>.thenEmit(value: T): ExpectationDefaultBuilder<T> =
    DefaultMaybeExpectationBuilder(this, value)

fun <T : Any> OngoingStubbing<Maybe<T>>.thenEmpty(): ExpectationDefaultBuilder<T> =
    DefaultMaybeExpectationBuilder(this, null)

class DefaultMaybeExpectationBuilder<T : Any>(
    private val ongoingStubbing: OngoingStubbing<Maybe<T>>,
    private val returnValue: T?
) : ExpectationDefaultBuilder<T> {

    override fun build(): Expectation {
        val expectation = ExpectationWithLatch()
        ongoingStubbing.thenAnswer {
            expectation.fulfilled()
            if (returnValue != null) {
                Maybe.just(returnValue)
            } else {
                Maybe.empty()
            }
        }

        return expectation
    }
}