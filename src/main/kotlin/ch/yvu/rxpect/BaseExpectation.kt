package ch.yvu.rxpect

import org.mockito.MockingDetails
import org.mockito.Mockito.mockingDetails
import org.mockito.exceptions.base.MockitoAssertionError
import org.mockito.internal.exceptions.Reporter.wantedButNotInvoked
import org.mockito.invocation.Invocation
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit.SECONDS

abstract class BaseExpectation : Expectation {
    companion object {
        const val verifyTimeoutInSeconds = 2L
    }

    private val latch: CountDownLatch = CountDownLatch(1)

    lateinit var invocation: Invocation
    lateinit var mock: Any

    override fun fulfilled() {
        latch.countDown()
    }

    override fun verify() {
        val result = latch.await(verifyTimeoutInSeconds, SECONDS)
        if (!result) {
            throw wantedButNotInvoked(invocation, mockingDetails(mock).invocations.toList())
        }
    }

    abstract fun buildAssertionError(invocation: Invocation, mockingDetails: MockingDetails): MockitoAssertionError
}