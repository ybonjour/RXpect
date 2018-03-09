package ch.yvu.rxpect.dispose

import ch.yvu.rxpect.Expectation
import ch.yvu.rxpect.ExpectationWithLatch
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import java.util.concurrent.CountDownLatch

fun <T> expectDispose(methodCall: Single<T>?): Expectation {
    val latch = CountDownLatch(1)
    whenever(methodCall).thenAnswer {
        Single.never<T>().doOnDispose { latch.countDown() }
    }
    return ExpectationWithLatch(latch)
}

fun <T> expectDispose(methodCall: Maybe<T>?): Expectation {
    val latch = CountDownLatch(1)
    whenever(methodCall).thenAnswer {
        Maybe.never<T>().doOnDispose { latch.countDown() }
    }
    return ExpectationWithLatch(latch)
}

fun <T> expectDispose(methodCall: Observable<T>?): Expectation {
    val latch = CountDownLatch(1)
    whenever(methodCall).thenAnswer {
        Observable.never<T>().doOnDispose { latch.countDown() }
    }
    return ExpectationWithLatch(latch)
}

