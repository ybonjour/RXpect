package ch.yvu.rxpect.dispose

import ch.yvu.rxpect.Expectation
import ch.yvu.rxpect.buildExpectation
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import org.mockito.stubbing.Answer

fun <T> expectDispose(methodCall: Single<T>?): Expectation =
    buildExpectation(whenever(methodCall)) { expectation ->
        Answer {
            Single.never<T>().doOnDispose { expectation.fulfilled() }
        }
    }

fun <T> expectDispose(methodCall: Maybe<T>?): Expectation =
    buildExpectation(whenever(methodCall)) { expectation ->
        Answer {
            Maybe.never<T>().doOnDispose { expectation.fulfilled() }
        }
    }

fun <T> expectDispose(methodCall: Observable<T>?): Expectation =
    buildExpectation(whenever(methodCall)) { expectation ->
        Answer {
            Observable.never<T>().doOnDispose { expectation.fulfilled() }
        }
    }
