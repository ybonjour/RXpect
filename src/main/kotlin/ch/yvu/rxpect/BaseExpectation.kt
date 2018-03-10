package ch.yvu.rxpect

import ch.yvu.rxpect.mockito.MockitoHelpers
import org.mockito.MockingDetails
import org.mockito.Mockito.mockingDetails
import org.mockito.exceptions.base.MockitoAssertionError
import org.mockito.invocation.Invocation
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.OngoingStubbing
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit.SECONDS

abstract class BaseExpectation : Expectation {
    companion object {
        const val verifyTimeoutInSeconds = 1L
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
            throw buildAssertionError(invocation, mockingDetails(mock))
        }
    }

    abstract fun buildAssertionError(invocation: Invocation, mockingDetails: MockingDetails): MockitoAssertionError
}

fun <T, U : BaseExpectation> setupExpectation(expectation: U, ongoingStubbing: OngoingStubbing<T>, answerFn: (Expectation) -> (InvocationOnMock) -> T?): U {
    ongoingStubbing.thenAnswer(answerFn(expectation))
    expectation.invocation = MockitoHelpers.extractLastInvocation(ongoingStubbing)
    expectation.mock = ongoingStubbing.getMock()
    return expectation
}