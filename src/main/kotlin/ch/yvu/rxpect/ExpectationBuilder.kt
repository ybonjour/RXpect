package ch.yvu.rxpect

import ch.yvu.rxpect.mockito.MockitoHelpers.extractLastInvocation
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.OngoingStubbing

interface ExpectationBuilder<T : Expectation> {
    fun build(): T
}

fun <T, U : BaseExpectation> setupExpectation(expectation: U, ongoingStubbing: OngoingStubbing<T>, answerFn: (Expectation) -> (InvocationOnMock) -> T?): U {
    ongoingStubbing.thenAnswer(answerFn(expectation))
    expectation.invocation = extractLastInvocation(ongoingStubbing)
    expectation.mock = ongoingStubbing.getMock()
    return expectation
}