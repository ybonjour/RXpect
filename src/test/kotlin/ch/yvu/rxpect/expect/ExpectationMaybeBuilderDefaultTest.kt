package ch.yvu.rxpect.expect

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Maybe
import org.junit.Test
import org.mockito.exceptions.verification.WantedButNotInvoked

class ExpectationMaybeBuilderDefaultTest {

    @Test
    fun buildsCorrectExpectationForMethodCalled() {
        val mock: TestClass = mock()
        val expectation = expect(whenever(mock.method()).thenEmit(Unit))

        mock.method()

        expectation.verify()
    }

    @Test(expected = WantedButNotInvoked::class)
    fun buildsCorrectExpectationForMethodNotCalled() {
        val mock: TestClass = mock()
        val expectation = expect(whenever(mock.method()).thenEmit(Unit))

        expectation.verify()
    }

    interface TestClass {
        fun method(): Maybe<Unit>
    }
}