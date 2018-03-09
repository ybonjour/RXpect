package ch.yvu.rxpect.expect

import ch.yvu.rxpect.Expectation
import ch.yvu.rxpect.buildExpectation
import io.reactivex.Observable
import org.mockito.stubbing.Answer
import org.mockito.stubbing.OngoingStubbing

fun <T : Any> OngoingStubbing<Observable<T>>.thenEmit(value: T): ExpectationDefaultBuilder<T> =
    ExpectationObservableBuilderDefault(this, value)

fun <T : Any> OngoingStubbing<Observable<T>>.thenEmpty(): ExpectationDefaultBuilder<T> =
    ExpectationObservableBuilderDefault(this, null)

class ExpectationObservableBuilderDefault<T : Any>(
    private val ongoingStubbing: OngoingStubbing<Observable<T>>,
    private val returnValue: T?
) : ExpectationDefaultBuilder<T> {

    override fun build(): Expectation =
        buildExpectation(ongoingStubbing) { expectation ->
            Answer {
                expectation.fulfilled()
                if (returnValue != null) {
                    Observable.just(returnValue)
                } else {
                    Observable.empty()
                }
            }
        }
}