package ch.yvu.rxpect

import io.reactivex.Maybe
import org.mockito.stubbing.OngoingStubbing
import java.util.concurrent.CountDownLatch

fun <T : Any> OngoingStubbing<Maybe<T>>.thenEmit(value: T): ExpectationBuilder<T> =
    ExpectationMaybeBuilderDefault(this, value)

fun <T : Any> OngoingStubbing<Maybe<T>>.thenEmpty(): ExpectationBuilder<T> =
    ExpectationMaybeBuilderDefault(this, null)

class ExpectationMaybeBuilderDefault<T : Any>(
    private val ongoingStubbing: OngoingStubbing<Maybe<T>>,
    private val returnValue: T?
) : ExpectationBuilder<T> {

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