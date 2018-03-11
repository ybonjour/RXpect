package ch.yvu.rxpect.subscribe

import ch.yvu.rxpect.BaseExpectation
import ch.yvu.rxpect.FulfillableExpectation
import org.mockito.MockingDetails
import org.mockito.exceptions.base.MockitoAssertionError
import org.mockito.exceptions.verification.WantedButNotInvoked
import org.mockito.invocation.Invocation

class SubscribeExpectation<T>(val subscribeExpectationBuilder: SubscribeExpectationBuilder<T>) : BaseExpectation() {
    override fun buildAssertionError(invocation: Invocation, mockingDetails: MockingDetails): MockitoAssertionError =
        WantedButNotInvoked("The ${invocation.method.returnType.simpleName} returned by ${invocation.method.name} has never been subscribed to.")

    fun thenEmit(value: T): FulfillableExpectation {
        subscribeExpectationBuilder.emittedValue(value)
        return this
    }
}