package ch.yvu.rxpect

import com.nhaarman.mockitokotlin2.mock
import org.junit.Before
import org.junit.Test
import org.mockito.exceptions.verification.WantedButNotInvoked

class ExpectationWithLatchTest {

    private lateinit var expectation: ExpectationWithLatch

    @Before
    fun setUp() {
        expectation = ExpectationWithLatch()
        expectation.mock = mock()
        expectation.stubbing = mock()
    }

    @Test
    fun verifyDoesNotThrowErrorIfExpectationIsFulfilled() {
        expectation.fulfilled()

        expectation.verify()
    }

    @Test(expected = WantedButNotInvoked::class)
    fun verifyThrowsWantedButNotInvokedErrorIfExpectationIsNotFulfilled() {
        expectation.verify()
    }
}
