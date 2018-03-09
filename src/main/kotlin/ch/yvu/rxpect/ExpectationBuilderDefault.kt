package ch.yvu.rxpect

import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import java.util.concurrent.CountDownLatch

class ExpectationBuilderDefault : ExpectationBuilder {
    override fun <T> forSingle(methodCall: Single<T>?, returnValue: T): Expectation {
        val latch = CountDownLatch(1)
        whenever(methodCall).thenAnswer {
            latch.countDown()
            Single.just(returnValue)
        }
        return ExpectationWithLatch(latch)
    }
}