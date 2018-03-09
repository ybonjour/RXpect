package ch.yvu.rxpect

import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Test
import org.mockito.exceptions.verification.WantedButNotInvoked

class ExpectationBuilderDefaultTest {

    @Test
    fun buildsCorrectExpectationForMethodCalled() {
        val mock: TestClass = mock()
        val expectationBuilder: ExpectationBuilder = ExpectationBuilderDefault()

        val expectation = expectationBuilder.forSingle(mock.method(), Unit)

        mock.method()

        expectation.verify()
    }

    @Test(expected = WantedButNotInvoked::class)
    fun buildsCorrectExpectationForMethodNotCalled() {
        val mock: TestClass = mock()
        val expectationBuilder: ExpectationBuilder = ExpectationBuilderDefault()

        val expectation = expectationBuilder.forSingle(mock.method(), Unit)

        expectation.verify()
    }

    interface TestClass {
        fun method(): Single<Unit>
    }
}