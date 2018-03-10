package ch.yvu.rxpect.expect

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import org.mockito.exceptions.verification.WantedButNotInvoked

class DefaultExpectationBuilderTest {
    @Test
    fun fulfilledExpectation() {
        val mock: TestClass = mock()
        val expectation = expect(mock.foo(any())).thenReturn(2)

        mock.foo("hello")

        expectation.verify()
    }

    @Test
    fun fulfilledExpectationWithNoReturnValue() {
        val mock: TestClass = mock()
        val expectation = expect(mock.foo(any()))

        mock.foo("hello")

        expectation.verify()
    }

    @Test(expected = WantedButNotInvoked::class)
    fun unfulfilledExpectation() {
        val mock: TestClass = mock()
        val expectation = expect(mock.foo(any())).thenReturn(2)

        expectation.verify()
    }

    interface TestClass {
        fun foo(value: String): Int
    }
}