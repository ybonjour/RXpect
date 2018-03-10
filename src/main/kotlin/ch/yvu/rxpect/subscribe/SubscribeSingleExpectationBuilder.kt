package ch.yvu.rxpect.subscribe

import ch.yvu.rxpect.Expectation
import ch.yvu.rxpect.mockito.defaultValue
import ch.yvu.rxpect.setupExpectation
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single

inline fun <reified T : Any> expectSubscribe(methodCall: Single<T>?): SubscribeExpectation<T> =
    SubscribeSingleExpectationBuilder(methodCall, defaultValueGenerator()).build() as SubscribeExpectation<T>

inline fun <reified T : Any> defaultValueGenerator(): () -> T =
    {
        defaultValue()
            ?: throw IllegalStateException("Please provide a return value as follows expectSubscribe(mock.foo()).thenReturn(returnValue)")
    }

class SubscribeSingleExpectationBuilder<T>(
    private val methodCall: Single<T>?,
    private val defaultAnswer: () -> T
) : SubscribeExpectationBuilder<T> {

    private var answer: () -> T =
        defaultAnswer

    override fun emittedValue(value: T) {
        this.answer = { value }
    }

    override fun build(): Expectation =
        setupExpectation(SubscribeExpectation(this), whenever(methodCall)) { expectation ->
            {
                Single.just(answer()).doOnSubscribe { expectation.fulfilled() }
            }
        }
}