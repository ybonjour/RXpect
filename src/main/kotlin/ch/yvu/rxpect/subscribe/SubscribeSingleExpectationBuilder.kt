package ch.yvu.rxpect.subscribe

import ch.yvu.rxpect.setupExpectation
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single

class SubscribeSingleExpectationBuilder<T>(
    private val methodCall: Single<T>?,
    private val defaultAnswer: () -> T
) : SubscribeExpectationBuilder<T> {

    private var answer: () -> T =
        defaultAnswer

    override fun emittedValue(value: T) {
        this.answer = { value }
    }

    override fun build(): SubscribeExpectation<T> =
        setupExpectation(SubscribeExpectation(this), whenever(methodCall)) { expectation ->
            {
                Single.just(answer()).doOnSubscribe { expectation.fulfilled() }
            }
        }
}