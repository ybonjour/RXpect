package ch.yvu.rxpect

import ch.yvu.rxpect.dispose.DisposeExpectationBuilder
import ch.yvu.rxpect.expect.DefaultExpectation
import ch.yvu.rxpect.expect.DefaultExpectationBuilderImpl
import ch.yvu.rxpect.mockito.defaultValueGenerator
import ch.yvu.rxpect.subscribe.SubscribeCompletableExpectationBuilder
import ch.yvu.rxpect.subscribe.SubscribeExpectation
import ch.yvu.rxpect.subscribe.SubscribeMaybeExpectationBuilder
import ch.yvu.rxpect.subscribe.SubscribeObservableExpectationBuilder
import ch.yvu.rxpect.subscribe.SubscribeSingleExpectationBuilder
import io.reactivex.Completable
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

    fun expectDispose(methodCall: Completable?): Expectation =
        DisposeExpectationBuilder.expectDispose(methodCall)

    fun <T> expectSubscribe(methodCall: Observable<T>?): SubscribeExpectation<T> =
        SubscribeObservableExpectationBuilder(methodCall, null).build()

    fun <T> expectSubscribe(methodCall: Maybe<T>?): SubscribeExpectation<T> =
        SubscribeMaybeExpectationBuilder(methodCall, null).build()

    fun expectSubscribe(methodCall: Completable?): Expectation =
        SubscribeCompletableExpectationBuilder(methodCall).build()

    inline fun <reified T : Any> expectSubscribe(methodCall: Single<T>?): SubscribeExpectation<T> =
        SubscribeSingleExpectationBuilder(
            methodCall,
            defaultValueGenerator("Please provide a return value as follows expectSubscribe(mock.foo()).thenReturn(returnValue)"))
            .build()

    inline fun <reified T> expect(methodCall: T): DefaultExpectation<T> =
        DefaultExpectationBuilderImpl(
            methodCall,
            defaultValueGenerator("Please provide a return value as follows expect(mock.foo()).thenReturn(returnValue)"))
            .build()
}