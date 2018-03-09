package ch.yvu.rxpect.dispose

import ch.yvu.rxpect.Expectation
import ch.yvu.rxpect.buildExpectation
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import org.mockito.stubbing.Answer

fun <T> expectDispose(methodCall: Single<T>?): Expectation =
    buildExpectation(methodCall) { expectation ->
        Answer {
            Single.never<T>().doOnDispose { expectation.fulfilled() }
        }
    }

fun <T> expectDispose(methodCall: Maybe<T>?): Expectation =
    buildExpectation(methodCall) { expectation ->
        Answer {
            Maybe.never<T>().doOnDispose { expectation.fulfilled() }
        }
    }

fun <T> expectDispose(methodCall: Observable<T>?): Expectation =
    buildExpectation(methodCall) { expectation ->
        Answer {
            Observable.never<T>().doOnDispose { expectation.fulfilled() }
        }
    }
