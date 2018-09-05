package ch.yvu.rxpect.mockito

import org.mockito.Mockito
import org.mockito.invocation.Invocation
import org.mockito.stubbing.OngoingStubbing
import org.mockito.stubbing.Stubbing
import java.util.LinkedList

object MockitoHelpers {
    fun <T> extractLastInvocation(ongoingStubbing: OngoingStubbing<T>): Invocation =
        getAllStubbings(ongoingStubbing.getMock()).last().invocation

    private fun getAllStubbings(mock: Any): List<Stubbing> {
        // We use the internal information, that Mockito tracks stubbings ordered in a LinkedList
        // so that we can extract the last (just added stubbing). This may break on Mockito updates
        return Mockito.mockingDetails(mock).stubbings as LinkedList
    }
}