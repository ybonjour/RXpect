package ch.yvu.rxpect.expect

import ch.yvu.rxpect.Expectation
import ch.yvu.rxpect.ExpectationBuilder

interface ExpectationDefaultBuilder<T> : ExpectationBuilder<T>

fun <T> expect(expectationBuilder: ExpectationDefaultBuilder<T>): Expectation =
    expectationBuilder.build()