package ch.yvu.rxpect.expect

import ch.yvu.rxpect.Expectation
import ch.yvu.rxpect.setupExpectation
import io.reactivex.Single
import org.mockito.stubbing.OngoingStubbing

fun <T : Any> OngoingStubbing<Single<T>>.thenEmit(value: T): ExpectationDefaultBuilder<T> =
    ExpectationSingleBuilderDefault(this, value)

class ExpectationSingleBuilderDefault<T : Any>(
    private val ongoingStubbing: OngoingStubbing<Single<T>>,
    private val returnValue: T
) : ExpectationDefaultBuilder<T> {

    override fun build(): Expectation =
        setupExpectation(DefaultExpectation(), ongoingStubbing) { expectation ->
            {
                expectation.fulfilled()
                Single.just(returnValue)
            }
        }
}