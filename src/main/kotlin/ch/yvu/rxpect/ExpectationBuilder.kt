package ch.yvu.rxpect

import io.reactivex.Single

interface ExpectationBuilder {
    fun <T> forSingle(methodCall: Single<T>?, returnValue: T): Expectation
}