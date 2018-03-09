package ch.yvu.rxpect.subscirbe

import ch.yvu.rxpect.Expectation
import ch.yvu.rxpect.ExpectationBuilder

interface SubscribeExpectationBuilder<T> : ExpectationBuilder<T>

fun <T> expectSubscribe(expectationBuilder: SubscribeExpectationBuilder<T>): Expectation =
    expectationBuilder.build()