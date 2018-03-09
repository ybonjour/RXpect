package ch.yvu.rxpect

import com.nhaarman.mockitokotlin2.mock
import org.junit.Before
import org.junit.Test
import org.mockito.exceptions.verification.WantedButNotInvoked

class BaseExpectationTest {

    private lateinit var baseExpectation: BaseExpectation

    @Before
    fun setUp() {
        baseExpectation = BaseExpectation()
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
