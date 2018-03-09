package ch.yvu.rxpect.subscirbe

import ch.yvu.rxpect.BaseExpectation
import org.mockito.MockingDetails
import org.mockito.exceptions.base.MockitoAssertionError
import org.mockito.exceptions.verification.WantedButNotInvoked
import org.mockito.invocation.Invocation

class SubscribeExpectation : BaseExpectation() {
    override fun buildAssertionError(invocation: Invocation, mockingDetails: MockingDetails): MockitoAssertionError =
        WantedButNotInvoked("The ${invocation.method.returnType} returned by ${invocation.method.name} has never been subscribed to.")
}