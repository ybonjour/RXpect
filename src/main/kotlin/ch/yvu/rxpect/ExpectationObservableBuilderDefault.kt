package ch.yvu.rxpect

import io.reactivex.Observable
import org.mockito.stubbing.OngoingStubbing
import java.util.concurrent.CountDownLatch

fun <T : Any> OngoingStubbing<Observable<T>>.thenEmit(value: T): ExpectationBuilder<T> =
    ExpectationObservableBuilderDefault(this, value)

fun <T : Any> OngoingStubbing<Observable<T>>.thenEmpty(): ExpectationBuilder<T> =
    ExpectationObservableBuilderDefault(this, null)

class ExpectationObservableBuilderDefault<T : Any>(
    private val ongoingStubbing: OngoingStubbing<Observable<T>>,
    private val returnValue: T?
) : ExpectationBuilder<T> {

    override fun build(): Expectation {
        val latch = CountDownLatch(1)
        ongoingStubbing.thenAnswer {
            latch.countDown()
            if (returnValue != null) {
                Observable.just(returnValue)
            } else {
                Observable.empty()
            }
        }

        return ExpectationWithLatch(latch)
    }
}