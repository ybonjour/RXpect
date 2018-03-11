package ch.yvu.rxpect.mockito

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class PackageExcludeStackTraceFilterTest {
    @Test
    fun filtersOutExcludedPackage() {
        val excludedPackage = "ch.yvu"
        val filter = PackageExcludeStackTraceFilter(listOf(excludedPackage))

        val result = filter.filter(arrayOf(stackTraceElementForPackage(excludedPackage)), false)

        assertThat(result, `is`(emptyArray()))
    }

    @Test
    fun keepsNotExcludedPackage() {
        val excludedPackage = "ch.yvu"
        val stackTraceElement = stackTraceElementForPackage("java.lang")
        val filter = PackageExcludeStackTraceFilter(listOf(excludedPackage))

        val result = filter.filter(arrayOf(stackTraceElement), false)

        assertThat(result, `is`(arrayOf(stackTraceElement)))
    }

    private fun stackTraceElementForPackage(p: String) =
        StackTraceElement("$p.Class", "foo", "foo", 42)
}