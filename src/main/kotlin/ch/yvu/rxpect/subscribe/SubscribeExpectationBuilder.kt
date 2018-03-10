package ch.yvu.rxpect.subscribe

import ch.yvu.rxpect.Expectation
import ch.yvu.rxpect.ExpectationBuilder

interface SubscribeExpectationBuilder<T> : ExpectationBuilder<T> {
    fun emittedValue(value: T)
}

fun <T> expectSubscribe(expectationBuilder: SubscribeExpectationBuilder<T>): Expectation =
    expectationBuilder.build()