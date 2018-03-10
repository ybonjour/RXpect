package ch.yvu.rxpect

import ch.yvu.rxpect.mockito.MockitoHelpers.extractLastInvocation
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.OngoingStubbing

interface ExpectationBuilder<T> {
    fun build(): Expectation
}

fun <T> setupExpectation(expectation: BaseExpectation, ongoingStubbing: OngoingStubbing<T>, answerFn: (Expectation) -> (InvocationOnMock) -> T): Expectation {
    ongoingStubbing.thenAnswer(answerFn(expectation))
    expectation.invocation = extractLastInvocation(ongoingStubbing)
    expectation.mock = ongoingStubbing.getMock()
    return expectation
}