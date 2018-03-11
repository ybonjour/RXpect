package ch.yvu.rxpect.expect

import ch.yvu.rxpect.BaseExpectation
import ch.yvu.rxpect.Expectation
import org.mockito.MockingDetails
import org.mockito.exceptions.base.MockitoAssertionError
import org.mockito.internal.exceptions.Reporter
import org.mockito.invocation.Invocation

class DefaultExpectation<T>(private val builder: DefaultExpectationBuilder<T>) : BaseExpectation() {
    override fun buildNotWantedButInvoked(invocation: Invocation): MockitoAssertionError =
        Reporter.neverWantedButInvoked(invocation, invocation.location)

    override fun buildWantedButNotInvoked(invocation: Invocation, mockingDetails: MockingDetails): MockitoAssertionError =
        Reporter.wantedButNotInvoked(invocation, mockingDetails.invocations.toList())

    fun thenReturn(value: T): Expectation {
        builder.returnValue(value)
        return this
    }
}