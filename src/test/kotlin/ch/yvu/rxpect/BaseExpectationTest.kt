package ch.yvu.rxpect

import com.nhaarman.mockitokotlin2.mock
import org.junit.Before
import org.junit.Test
import org.mockito.MockingDetails
import org.mockito.exceptions.base.MockitoAssertionError
import org.mockito.exceptions.verification.WantedButNotInvoked
import org.mockito.invocation.Invocation
import org.mockito.invocation.Location

class BaseExpectationTest {

    private lateinit var baseExpectation: BaseExpectation

    @Before
    fun setUp() {
        baseExpectation = TestExpectation()
        baseExpectation.mock = mock()
        baseExpectation.invocation = mock()
    }

    @Test
    fun verifyDoesNotThrowErrorIfExpectationIsFulfilled() {
        baseExpectation.fulfilled()

        baseExpectation.verify()
    }

    @Test(expected = WantedButNotInvoked::class)
    fun verifyThrowsWantedButNotInvokedErrorIfExpectationIsNotFulfilled() {
        baseExpectation.verify()
    }

    @Test(expected = MockitoAssertionError::class)
    fun verifyNotFulfilledThrowsExceptionIfExpectationIsFulfilled() {
        baseExpectation.fulfilled()

        baseExpectation.verifyNotFulfilled()
    }

    @Test
    fun verifyNotFulfilledDoesNotThrowsExceptionIfExpectationIsNotFulfilled() {
        baseExpectation.verifyNotFulfilled()
    }

    class TestExpectation : BaseExpectation() {
        override fun buildNotWantedButInvoked(invocation: Invocation, location: Location): MockitoAssertionError =
            MockitoAssertionError("Never wanted but invoked")

        override fun buildWantedButNotInvoked(invocation: Invocation, mockingDetails: MockingDetails): MockitoAssertionError {
            throw WantedButNotInvoked("Wanted but not invoked")
        }
    }
}
