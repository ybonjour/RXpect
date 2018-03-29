package ch.yvu.rxpect

import ch.yvu.rxpect.mockito.MockitoHelpers
import ch.yvu.rxpect.mockito.locationFiltered
import org.mockito.MockingDetails
import org.mockito.Mockito.mockingDetails
import org.mockito.exceptions.base.MockitoAssertionError
import org.mockito.invocation.Invocation
import org.mockito.invocation.InvocationOnMock
import org.mockito.invocation.Location
import org.mockito.stubbing.OngoingStubbing
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit.MILLISECONDS

abstract class BaseExpectation : FulfillableExpectation {
    companion object {
        const val verifyTimeoutInMilliSeconds = 100L
    }

    private val latch: CountDownLatch = CountDownLatch(1)

    lateinit var invocation: Invocation
    lateinit var mock: Any
    private var invocationLocation: Location? = null

    override fun fulfilled() {
        this.invocationLocation = locationFiltered()
        latch.countDown()
    }

    override fun verify() {
        val result = latch.await(verifyTimeoutInMilliSeconds, MILLISECONDS)
        if (!result) {
            throw buildWantedButNotInvoked(invocation, mockingDetails(mock))
        }
    }

    override fun verifyNotFulfilled() {
        val result = latch.await(verifyTimeoutInMilliSeconds, MILLISECONDS)
        if (result) {
            invocationLocation.let {
                if (it == null) {
                    throw IllegalStateException("The Location of the actual method invocation was never set.")
                } else {
                    throw buildNotWantedButInvoked(invocation, it)
                }
            }
        }
    }

    abstract fun buildNotWantedButInvoked(invocation: Invocation, location: Location): MockitoAssertionError
    abstract fun buildWantedButNotInvoked(invocation: Invocation, mockingDetails: MockingDetails): MockitoAssertionError
}

fun <T, U : BaseExpectation> setupExpectation(expectation: U, ongoingStubbing: OngoingStubbing<T>, answerFn: (FulfillableExpectation) -> (InvocationOnMock) -> T?): U {
    ongoingStubbing.thenAnswer(answerFn(expectation))
    expectation.invocation = MockitoHelpers.extractLastInvocation(ongoingStubbing)
    expectation.mock = ongoingStubbing.getMock()
    return expectation
}