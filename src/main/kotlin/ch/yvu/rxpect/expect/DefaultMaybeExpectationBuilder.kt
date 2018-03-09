package ch.yvu.rxpect.expect

import ch.yvu.rxpect.Expectation
import ch.yvu.rxpect.ExpectationBuilder
import ch.yvu.rxpect.ExpectationWithLatch
import io.reactivex.Maybe
import org.mockito.stubbing.OngoingStubbing
import java.util.concurrent.CountDownLatch

fun <T : Any> OngoingStubbing<Maybe<T>>.thenEmit(value: T): ExpectationDefaultBuilder<T> =
    ExpectationMaybeBuilderDefault(this, value)

fun <T : Any> OngoingStubbing<Maybe<T>>.thenEmpty(): ExpectationDefaultBuilder<T> =
    ExpectationMaybeBuilderDefault(this, null)

class ExpectationMaybeBuilderDefault<T : Any>(
    private val ongoingStubbing: OngoingStubbing<Maybe<T>>,
    private val returnValue: T?
) : ExpectationDefaultBuilder<T> {

    override fun build(): Expectation {
        val latch = CountDownLatch(1)
        ongoingStubbing.thenAnswer {
            latch.countDown()
            if (returnValue != null) {
                Maybe.just(returnValue)
            } else {
                Maybe.empty()
            }
        }

        return ExpectationWithLatch(latch)
    }
}