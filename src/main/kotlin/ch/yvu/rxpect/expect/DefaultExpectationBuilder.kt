package ch.yvu.rxpect.expect

import ch.yvu.rxpect.Expectation
import ch.yvu.rxpect.ExpectationBuilder
import ch.yvu.rxpect.mockito.defaultValue
import ch.yvu.rxpect.setupExpectation
import com.nhaarman.mockitokotlin2.whenever

inline fun <reified T : Any> expect(methodCall: T): DefaultExpectation<T> =
    DefaultExpectationBuilderImpl(methodCall, defaultValueGenerator()).build() as DefaultExpectation<T>

inline fun <reified T : Any> defaultValueGenerator(): () -> T =
    {
        defaultValue()
            ?: throw IllegalStateException("Please provide a return value as follows expect(mock.foo()).thenReturn(returnValue)")
    }

interface DefaultExpectationBuilder<T> : ExpectationBuilder<T> {
    fun returnValue(value: T)
}

class DefaultExpectationBuilderImpl<T : Any>(
    private val methodCall: T,
    private val defaultAnswer: () -> T
) : DefaultExpectationBuilder<T> {

    private var answer: () -> T =
        defaultAnswer

    override fun returnValue(value: T) {
        this.answer = { value }
    }

    override fun build(): Expectation =
        setupExpectation(DefaultExpectation(this), whenever(methodCall)) { expectation ->
            {
                expectation.fulfilled()
                answer()
            }
        }
}