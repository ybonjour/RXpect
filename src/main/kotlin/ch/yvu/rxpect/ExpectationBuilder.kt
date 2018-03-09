package ch.yvu.rxpect

import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.OngoingStubbing

interface ExpectationBuilder<T> {
    fun build(): Expectation
}

fun <T> buildExpectation(ongoingStubbing: OngoingStubbing<T>, answerFn: (Expectation) -> (InvocationOnMock) -> T): Expectation {
    val expectation = ExpectationWithLatch()
    ongoingStubbing.thenAnswer(answerFn(expectation))
    return expectation
}