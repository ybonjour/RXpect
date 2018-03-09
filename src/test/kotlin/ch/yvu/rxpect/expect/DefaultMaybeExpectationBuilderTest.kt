package ch.yvu.rxpect.expect

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Maybe
import org.junit.Test
import org.mockito.exceptions.verification.WantedButNotInvoked

class DefaultMaybeExpectationBuilderTest {
    @Test
    fun buildsCorrectExpectationForMethodCalled() {
        val mock: TestClass = mock()
        val expectation = expect(whenever(mock.foo("bar")).thenEmit(Unit))

        mock.foo("bar")

        expectation.verify()
    }

    @Test(expected = WantedButNotInvoked::class)
    fun buildsCorrectExpectationForMethodNotCalled() {
        val mock: TestClass = mock()
        val expectation = expect(whenever(mock.foo("foo")).thenEmit(Unit))

        expectation.verify()
    }

    interface TestClass {
        fun foo(value: String): Maybe<Unit>
    }
}