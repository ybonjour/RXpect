package ch.yvu.rxpect.expect

import ch.yvu.rxpect.ExpectationBuilder

interface DefaultExpectationBuilder<T> : ExpectationBuilder<DefaultExpectation<T>> {
    fun returnValue(value: T)
}