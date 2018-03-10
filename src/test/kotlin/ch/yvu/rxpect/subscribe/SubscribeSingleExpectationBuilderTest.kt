package ch.yvu.rxpect.subscribe

import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Test
import org.mockito.exceptions.verification.WantedButNotInvoked

class SubscribeSingleExpectationBuilderTest {
    @Test
    fun buildsCorrectExpectationForMethodCalled() {
        val mock: TestClass = mock()
        val expectation = expectSubscribe(mock.method()).thenEmit(Unit)

        mock.method().subscribe()

        expectation.verify()
    }

    @Test
    fun buildsCorrectExpectationForMethodCalledWithoutemittedValue() {
        val mock: TestClass = mock()
        val expectation = expectSubscribe(mock.method())

        mock.method().subscribe()

        expectation.verify()
    }

    @Test(expected = WantedButNotInvoked::class)
    fun buildsCorrectExpectationForMethodNotCalled() {
        val mock: TestClass = mock()
        val expectation = expectSubscribe(mock.method()).thenEmit(Unit)

        mock.method()

        expectation.verify()
    }

    interface TestClass {
        fun method(): Single<Unit>
    }
}