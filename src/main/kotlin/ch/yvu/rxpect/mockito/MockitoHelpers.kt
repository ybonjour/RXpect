package ch.yvu.rxpect.mockito

import org.mockito.Mockito
import org.mockito.invocation.Invocation
import org.mockito.stubbing.OngoingStubbing
import org.mockito.stubbing.Stubbing
import java.util.TreeSet

object MockitoHelpers {
    fun <T> extractLastInvocation(ongoingStubbing: OngoingStubbing<T>): Invocation {
        // We use the internal information, that Mockito tracks stubbings ordered in a TreeSet
        // so that we can extract the last (just added stubbing). This may break on Mockito updates
        val stubbings = Mockito.mockingDetails(ongoingStubbing.getMock()).stubbings as TreeSet<Stubbing>
        return stubbings.last().invocation
    }
}