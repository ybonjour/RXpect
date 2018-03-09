package ch.yvu.rxpect

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.exceptions.verification.WantedButNotInvoked
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit.SECONDS

class ExpectationWithLatchTest {

    @Test
    fun verifyAwaitsLatchWithTimeout() {
        val latch: CountDownLatch = mock { on { await(any(), any()) } doReturn true }
        val expectation: Expectation = ExpectationWithLatch(latch)

        expectation.verify()

        verify(latch).await(ExpectationWithLatch.verifyTimeoutInSeconds, SECONDS)
    }

    @Test(expected = WantedButNotInvoked::class)
    fun verifyThrowsWantedButNotInvokdedErrorIfLatchTimesOut() {
        val latch: CountDownLatch = mock { on { await(any(), any()) } doReturn false }
        val expectation: Expectation = ExpectationWithLatch(latch)

        expectation.verify()
    }
}
