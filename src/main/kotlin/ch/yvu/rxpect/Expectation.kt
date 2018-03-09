package ch.yvu.rxpect

interface Expectation {
    fun fulfilled()
    fun verify()
}