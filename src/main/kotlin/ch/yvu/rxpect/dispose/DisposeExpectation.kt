package ch.yvu.rxpect.dispose

import ch.yvu.rxpect.BaseExpectation
import org.mockito.MockingDetails
import org.mockito.exceptions.base.MockitoAssertionError
import org.mockito.exceptions.verification.WantedButNotInvoked
import org.mockito.invocation.Invocation

class DisposeExpectation : BaseExpectation() {
    override fun buildNotWantedButInvoked(invocation: Invocation): MockitoAssertionError =
        MockitoAssertionError("The ${invocation.method.returnType.simpleName} returned by ${invocation.method.name} was expected to never be disposed. But it has been disposed.")

    override fun buildWantedButNotInvoked(invocation: Invocation, mockingDetails: MockingDetails): MockitoAssertionError =
        WantedButNotInvoked("The ${invocation.method.returnType.simpleName} returned by ${invocation.method.name} has never been disposed.")
}