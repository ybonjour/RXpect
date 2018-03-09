package ch.yvu.rxpect.expect

import ch.yvu.rxpect.Expectation
import ch.yvu.rxpect.ExpectationBuilder
import ch.yvu.rxpect.ExpectationWithLatch
import io.reactivex.Single
import org.mockito.stubbing.OngoingStubbing
import java.util.concurrent.CountDownLatch

fun <T : Any> OngoingStubbing<Single<T>>.thenEmit(value: T): ExpectationBuilder<T> =
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