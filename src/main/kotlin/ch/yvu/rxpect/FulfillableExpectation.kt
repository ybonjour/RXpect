package ch.yvu.rxpect

interface FulfillableExpectation {
    fun fulfilled()
    fun verify()
}