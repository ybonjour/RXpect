package ch.yvu.rxpect.expect

import ch.yvu.rxpect.BaseExpectation
import org.mockito.MockingDetails
import org.mockito.exceptions.base.MockitoAssertionError
import org.mockito.internal.exceptions.Reporter
import org.mockito.invocation.Invocation

class DefaultExpectation : BaseExpectation() {
    override fun buildAssertionError(invocation: Invocation, mockingDetails: MockingDetails): MockitoAssertionError =
        Reporter.wantedButNotInvoked(invocation, mockingDetails.invocations.toList())
}