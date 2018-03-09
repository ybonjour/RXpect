package ch.yvu.rxpect

interface ExpectationBuilder<T> {
    fun build(): Expectation
}

fun <T> expect(expectationBuilder: ExpectationBuilder<T>): Expectation =
    expectationBuilder.build()