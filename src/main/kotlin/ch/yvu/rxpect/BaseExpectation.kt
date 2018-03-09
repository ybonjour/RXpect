package ch.yvu.rxpect

import org.mockito.Mockito.mockingDetails
import org.mockito.internal.exceptions.Reporter.wantedButNotInvoked
import org.mockito.invocation.Invocation
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit.SECONDS

class BaseExpectation : Expectation {
    companion object {
        const val verifyTimeoutInSeconds = 2L
    }

    private val latch: CountDownLatch = CountDownLatch(1)

    var invocation: Invocation? = null
    var mock: Any? = null

    override fun fulfilled() {
        latch.countDown()
    }

    override fun verify() {
        val result = latch.await(verifyTimeoutInSeconds, SECONDS)
        if (!result) {
            throw wantedButNotInvoked(invocation, mockingDetails(mock).invocations.toList())
        }
    }
}