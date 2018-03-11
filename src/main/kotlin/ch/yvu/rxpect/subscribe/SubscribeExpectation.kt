package ch.yvu.rxpect.subscribe

import ch.yvu.rxpect.BaseExpectation
import ch.yvu.rxpect.Expectation
import org.mockito.MockingDetails
import org.mockito.exceptions.base.MockitoAssertionError
import org.mockito.exceptions.verification.WantedButNotInvoked
import org.mockito.invocation.Invocation

class SubscribeExpectation<T>(val subscribeExpectationBuilder: SubscribeExpectationBuilder<T>) : BaseExpectation() {
    override fun buildNotWantedButInvoked(invocation: Invocation): MockitoAssertionError =
        MockitoAssertionError("The ${invocation.method.returnType.simpleName} returned by ${invocation.method.name} was expected to never been subscribed to. But it was subscribed to.")

    override fun buildWantedButNotInvoked(invocation: Invocation, mockingDetails: MockingDetails): MockitoAssertionError =
        WantedButNotInvoked("The ${invocation.method.returnType.simpleName} returned by ${invocation.method.name} has never been subscribed to.")

    fun thenEmit(value: T): Expectation {
        subscribeExpectationBuilder.emittedValue(value)
        return this
    }
}