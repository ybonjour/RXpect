package ch.yvu.rxpect.expect

import ch.yvu.rxpect.ExpectationBuilder
import ch.yvu.rxpect.mockito.defaultValueGenerator

inline fun <reified T : Any> expect(methodCall: T): DefaultExpectation<T> =
    DefaultExpectationBuilderImpl(
        methodCall,
        defaultValueGenerator("Please provide a return value as follows expect(mock.foo()).thenReturn(returnValue)"))
        .build()

interface DefaultExpectationBuilder<T> : ExpectationBuilder<DefaultExpectation<T>> {
    fun returnValue(value: T)
}