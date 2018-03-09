package ch.yvu.rxpect.subscirbe

import ch.yvu.rxpect.Expectation
import ch.yvu.rxpect.ExpectationWithLatch
import io.reactivex.Maybe
import org.mockito.stubbing.OngoingStubbing
import java.util.concurrent.CountDownLatch

fun <T> OngoingStubbing<Maybe<T>>.thenEmit(value: T): SubscribeExpectationBuilder<T> =
    SubscribeMaybeExpectationBuilder(this, value)

fun <T> OngoingStubbing<Maybe<T>>.thenEmpty(): SubscribeExpectationBuilder<T> =
    SubscribeMaybeExpectationBuilder(this, null)

class SubscribeMaybeExpectationBuilder<T>(
    private val ongoingStubbing: OngoingStubbing<Maybe<T>>,
    private val value: T?
) : SubscribeExpectationBuilder<T> {
    override fun build(): Expectation {
        val latch = CountDownLatch(1)
        ongoingStubbing.thenAnswer {
            if (value != null) {
                Maybe.just(value).doOnSubscribe { latch.countDown() }
            } else {
                Maybe.empty()
            }
        }

        return ExpectationWithLatch(latch)
    }
}