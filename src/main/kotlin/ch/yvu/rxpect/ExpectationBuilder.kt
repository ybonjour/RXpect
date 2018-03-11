package ch.yvu.rxpect

interface ExpectationBuilder<T : FulfillableExpectation> {
    fun build(): T
}