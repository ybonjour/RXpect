package ch.yvu.rxpect

interface Expectation {
    fun verify()
    fun verifyNotFulfilled()
}