package ch.yvu.rxpect

import com.nhaarman.mockitokotlin2.mock
import org.junit.Before
import org.junit.Test
import org.mockito.MockingDetails
import org.mockito.exceptions.base.MockitoAssertionError
import org.mockito.exceptions.verification.WantedButNotInvoked
import org.mockito.invocation.Invocation

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

    class TestExpectation : BaseExpectation() {
        override fun buildAssertionError(invocation: Invocation, mockingDetails: MockingDetails): MockitoAssertionError {
            throw WantedButNotInvoked("Wanted but not invoked")
        }
    }
}
