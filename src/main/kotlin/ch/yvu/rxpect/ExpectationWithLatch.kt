package ch.yvu.rxpect

import org.mockito.exceptions.verification.WantedButNotInvoked
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit.SECONDS

class ExpectationWithLatch(private val latch: CountDownLatch) : Expectation {
    companion object {
        const val verifyTimeoutInSeconds = 2L
    }

    override fun verify() {
        val result = latch.await(verifyTimeoutInSeconds, SECONDS)
        if (!result) {
            throw WantedButNotInvoked("Method was not called within $verifyTimeoutInSeconds second")
        }
    }
}