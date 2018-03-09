package ch.yvu.rxpect

interface ExpectationBuilder<T> {
    fun build(): Expectation
}