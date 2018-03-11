package ch.yvu.rxpect

interface FulfillableExpectation : Expectation {
    fun fulfilled()
}