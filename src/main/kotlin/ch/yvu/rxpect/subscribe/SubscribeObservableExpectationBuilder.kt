package ch.yvu.rxpect.subscribe

import ch.yvu.rxpect.setupExpectation
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Maybe

class SubscribeMaybeExpectationBuilder<T>(
    private val methodCall: Maybe<T>?,
    private val defaultValue: T?
) : SubscribeExpectationBuilder<T> {
    private var value: T? = defaultValue

    override fun emittedValue(value: T) {
        this.value = value
    }

    override fun build(): SubscribeExpectation<T> =
        setupExpectation(SubscribeExpectation(this), whenever(methodCall)) { expectation ->
            {
                value.let {
                    val maybe = if (it != null) {
                        Maybe.just(it)
                    } else {
                        Maybe.empty()
                    }
                    maybe.doOnSubscribe { expectation.fulfilled() }
                }
            }
        }
}