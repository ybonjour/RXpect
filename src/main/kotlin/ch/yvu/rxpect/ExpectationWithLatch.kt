package ch.yvu.rxpect

import org.mockito.exceptions.verification.WantedButNotInvoked
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit.SECONDS

class ExpectationWithLatch : Expectation {
    companion object {
        const val verifyTimeoutInSeconds = 2L
    }

    private val latch: CountDownLatch = CountDownLatch(1)

    override fun fulfilled() {
        latch.countDown()
    }

    override fun verify() {
        val result = latch.await(verifyTimeoutInSeconds, SECONDS)
        if (!result) {
            throw WantedButNotInvoked("Method was not called within $verifyTimeoutInSeconds second")
        }
    }
}