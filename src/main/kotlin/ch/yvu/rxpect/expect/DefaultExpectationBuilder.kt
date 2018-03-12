package ch.yvu.rxpect.expect

import ch.yvu.rxpect.ExpectationBuilder
import ch.yvu.rxpect.mockito.defaultValue

inline fun <reified T : Any> expect(methodCall: T): DefaultExpectation<T> =
    DefaultExpectationBuilderImpl(methodCall, defaultValueGenerator()).build()

inline fun <reified T : Any> defaultValueGenerator(): () -> T =
    {
        defaultValue()
            ?: throw IllegalStateException("Please provide a return value as follows expect(mock.foo()).thenReturn(returnValue)")
    }

interface DefaultExpectationBuilder<T> : ExpectationBuilder<DefaultExpectation<T>> {
    fun returnValue(value: T)
}