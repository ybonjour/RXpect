package ch.yvu.rxpect

import org.mockito.Mockito
import org.mockito.exceptions.verification.WantedButNotInvoked
import org.mockito.invocation.Invocation
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit.SECONDS

class ExpectationWithLatch : Expectation {
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
            throw WantedButNotInvoked(buildMessage())
        }
    }

    private fun buildMessage(): String {
        var message = "${methodName()} was not called within $verifyTimeoutInSeconds second"
        val matchingInvocation = matchingInvocation()
        if (matchingInvocation != null) {
            message += "\n There was a matching invocation"
        }
        return message
    }

    private fun matchingInvocation(): Invocation? =
        Mockito.mockingDetails(mock).invocations.find {
            it.method == invocation?.method
        }

    private fun methodName() =
        invocation?.method?.name ?: "<method unknown>"
}