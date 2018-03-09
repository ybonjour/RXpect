package ch.yvu.rxpect

import io.reactivex.Single
import org.mockito.stubbing.OngoingStubbing
import java.util.concurrent.CountDownLatch

fun <U : Any> OngoingStubbing<Single<U>>.thenEmit(value: U): ExpectationBuilder<U> =
    ExpectationSingleBuilderDefault(this, value)

class ExpectationSingleBuilderDefault<T : Any>(
    private val ongoingStubbing: OngoingStubbing<Single<T>>,
    private val returnValue: T
) : ExpectationBuilder<T> {

    override fun build(): Expectation {
        val latch = CountDownLatch(1)
        ongoingStubbing.thenAnswer {
            latch.countDown()
            Single.just(returnValue)
        }

        return ExpectationWithLatch(latch)
    }
}