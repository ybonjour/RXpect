package ch.yvu.rxpect

import org.mockito.stubbing.Answer
import org.mockito.stubbing.OngoingStubbing

interface ExpectationBuilder<T> {
    fun build(): Expectation
}

fun <T> buildExpectation(ongoingStubbing: OngoingStubbing<T>, answerFn: (Expectation) -> Answer<T>): Expectation {
    val expectation = ExpectationWithLatch()
    ongoingStubbing.thenAnswer(answerFn(expectation))
    return expectation
}