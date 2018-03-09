package ch.yvu.rxpect.subscirbe

import ch.yvu.rxpect.Expectation
import ch.yvu.rxpect.ExpectationWithLatch
import io.reactivex.Single
import org.mockito.stubbing.OngoingStubbing

fun <T> OngoingStubbing<Single<T>>.thenEmit(value: T): SubscribeExpectationBuilder<T> =
    SubscribeSingleExpectationBuilder(this, value)

class SubscribeSingleExpectationBuilder<T>(
    private val ongoingStubbing: OngoingStubbing<Single<T>>,
    private val value: T
) : SubscribeExpectationBuilder<T> {
    override fun build(): Expectation {
        val expectation = ExpectationWithLatch()
        ongoingStubbing.thenAnswer {
            Single.just(value).doOnSubscribe { expectation.fulfilled() }
        }

        return expectation
    }
}