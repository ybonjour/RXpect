package ch.yvu.rxpect.mockito

import org.mockito.internal.exceptions.stacktrace.StackTraceFilter

class PackageExcludeStackTraceFilter(private val excludedPackages: List<String>) : StackTraceFilter() {

    override fun filter(target: Array<out StackTraceElement>?, keepTop: Boolean): Array<StackTraceElement> {
        val candidates = super.filter(target, keepTop)
        return candidates.filter { !isExcluded(it) }.toTypedArray()
    }

    private fun isExcluded(candidate: StackTraceElement): Boolean =
        excludedPackages.any {
            candidate.className.startsWith(it)
        }
}