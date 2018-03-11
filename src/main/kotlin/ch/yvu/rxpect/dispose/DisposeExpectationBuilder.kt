package ch.yvu.rxpect.dispose

import ch.yvu.rxpect.FulfillableExpectation
import ch.yvu.rxpect.setupExpectation
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

fun <T> expectDispose(methodCall: Single<T>?): FulfillableExpectation =
    setupExpectation(DisposeExpectation(), whenever(methodCall)) { expectation ->
        {
            Single.never<T>().doOnDispose { expectation.fulfilled() }
        }
    }

fun <T> expectDispose(methodCall: Maybe<T>?): FulfillableExpectation =
    setupExpectation(DisposeExpectation(), whenever(methodCall)) { expectation ->
        {
            Maybe.never<T>().doOnDispose { expectation.fulfilled() }
        }
    }

fun <T> expectDispose(methodCall: Observable<T>?): FulfillableExpectation =
    setupExpectation(DisposeExpectation(), whenever(methodCall)) { expectation ->
        {
            Observable.never<T>().doOnDispose { expectation.fulfilled() }
        }
    }
