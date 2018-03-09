package ch.yvu.rxpect.dispose

import ch.yvu.rxpect.Expectation
import ch.yvu.rxpect.ExpectationWithLatch
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

fun <T> expectDispose(methodCall: Single<T>?): Expectation {
    val expectation = ExpectationWithLatch()
    whenever(methodCall).thenAnswer {
        Single.never<T>().doOnDispose { expectation.fulfilled() }
    }
    return expectation
}

fun <T> expectDispose(methodCall: Maybe<T>?): Expectation {
    val expectation = ExpectationWithLatch()
    whenever(methodCall).thenAnswer {
        Maybe.never<T>().doOnDispose { expectation.fulfilled() }
    }
    return expectation
}

fun <T> expectDispose(methodCall: Observable<T>?): Expectation {
    val expectation = ExpectationWithLatch()
    whenever(methodCall).thenAnswer {
        Observable.never<T>().doOnDispose { expectation.fulfilled() }
    }
    return expectation
}
