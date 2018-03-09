package ch.yvu.rxpect

import com.nhaarman.mockitokotlin2.whenever
import org.mockito.stubbing.Answer

interface ExpectationBuilder<T> {
    fun build(): Expectation
}

fun <T> buildExpectation(methodCall: T, answerFn: (Expectation) -> Answer<T>): Expectation {
    val expectation = ExpectationWithLatch()
    whenever(methodCall).thenAnswer(answerFn(expectation))
    return expectation
}