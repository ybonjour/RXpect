package ch.yvu.rxpect.dispose

import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Test
import org.mockito.exceptions.verification.WantedButNotInvoked

class DisposeSingleExpectationBuilderTest {
    @Test
    fun buildsCorrectExpectationForSingleDisposed() {
        val mock: TestClass = mock()
        val expectation = expectDispose(mock.method())

        val disposable = mock.method().subscribe()
        disposable.dispose()

        expectation.verify()
    }

    @Test(expected = WantedButNotInvoked::class)
    fun buildsCorrectExpectationForSingleNotDisposed() {
        val mock: TestClass = mock()
        val expectation = expectDispose(mock.method())

        mock.method().subscribe()

        expectation.verify()
    }

    interface TestClass {
        fun method(): Single<Unit>
    }
}