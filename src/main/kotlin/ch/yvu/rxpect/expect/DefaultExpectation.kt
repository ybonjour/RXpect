package ch.yvu.rxpect.expect

import ch.yvu.rxpect.BaseExpectation
import ch.yvu.rxpect.Expectation
import org.mockito.MockingDetails
import org.mockito.exceptions.base.MockitoAssertionError
import org.mockito.internal.exceptions.Reporter
import org.mockito.invocation.Invocation
import org.mockito.invocation.Location

class DefaultExpectation<T>(private val builder: DefaultExpectationBuilder<T>) : BaseExpectation() {
    override fun buildNotWantedButInvoked(invocation: Invocation, location: Location): MockitoAssertionError =
        Reporter.neverWantedButInvoked(invocation, location)

    override fun buildWantedButNotInvoked(invocation: Invocation, mockingDetails: MockingDetails): MockitoAssertionError =
        Reporter.wantedButNotInvoked(invocation, mockingDetails.invocations.toList())

    fun thenReturn(value: T): Expectation {
        builder.returnValue(value)
        return this
    }
}