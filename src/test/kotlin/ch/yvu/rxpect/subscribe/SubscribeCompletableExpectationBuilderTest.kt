package ch.yvu.rxpect.subscribe

import ch.yvu.rxpect.RXpect.expectSubscribe
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Completable
import org.junit.Test
import org.mockito.exceptions.verification.WantedButNotInvoked

class SubscribeCompletableExpectationBuilderTest {
    @Test
    fun buildsCorrectExpectationForMethodCalled() {
        val mock: TestClass = mock()
        val expectation = expectSubscribe(mock.method())

        mock.method().subscribe()

        expectation.verify()
    }

    @Test(expected = WantedButNotInvoked::class)
    fun buildsCorrectExpectationForMethodNotCalled() {
        val mock: TestClass = mock()
        val expectation = expectSubscribe(mock.method())

        mock.method()

        expectation.verify()
    }

    interface TestClass {
        fun method(): Completable
    }
}