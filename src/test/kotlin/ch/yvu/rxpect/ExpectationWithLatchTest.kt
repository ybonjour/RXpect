package ch.yvu.rxpect

import org.junit.Test
import org.mockito.exceptions.verification.WantedButNotInvoked

class ExpectationWithLatchTest {

    @Test
    fun verifyDoesNotThrowErrorIfExpectationIsFulfilled() {
        val expectation: Expectation = ExpectationWithLatch()
        expectation.fulfilled()

        expectation.verify()
    }

    @Test(expected = WantedButNotInvoked::class)
    fun verifyThrowsWantedButNotInvokedErrorIfExpectationIsNotFulfilled() {
        val expectation: Expectation = ExpectationWithLatch()

        expectation.verify()
    }
}
