package ch.yvu.rxpect

import ch.yvu.rxpect.expect.DefaultExpectation
import com.nhaarman.mockitokotlin2.mock
import org.junit.Before
import org.junit.Test
import org.mockito.exceptions.verification.WantedButNotInvoked

class BaseExpectationTest {

    private lateinit var baseExpectation: BaseExpectation

    @Before
    fun setUp() {
        baseExpectation = DefaultExpectation()
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
}
