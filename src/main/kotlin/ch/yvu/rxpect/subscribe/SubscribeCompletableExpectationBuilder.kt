package ch.yvu.rxpect.subscribe

import ch.yvu.rxpect.setupExpectation
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable

class SubscribeCompletableExpectationBuilder(
    private val methodCall: Completable?
) : SubscribeExpectationBuilder<Unit> {

    override fun emittedValue(value: Unit) {
        throw Exception("A completable can not emit a value")
    }

    override fun build(): SubscribeExpectation<Unit> =
        setupExpectation(SubscribeExpectation(this), whenever(methodCall)) { expectation ->
            {
                Completable.complete().doOnSubscribe { expectation.fulfilled() }
            }
        }
}