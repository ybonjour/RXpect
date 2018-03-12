package ch.yvu.rxpect

import ch.yvu.rxpect.dispose.DisposeExpectationBuilder
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

object RXpect {
    fun <T> expectDispose(methodCall: Single<T>?): Expectation =
        DisposeExpectationBuilder.expectDispose(methodCall)

    fun <T> expectDispose(methodCall: Maybe<T>?): Expectation =
        DisposeExpectationBuilder.expectDispose(methodCall)

    fun <T> expectDispose(methodCall: Observable<T>?): Expectation =
        DisposeExpectationBuilder.expectDispose(methodCall)
}