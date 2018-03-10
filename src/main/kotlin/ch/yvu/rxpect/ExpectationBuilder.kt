package ch.yvu.rxpect

interface ExpectationBuilder<T : Expectation> {
    fun build(): T
}