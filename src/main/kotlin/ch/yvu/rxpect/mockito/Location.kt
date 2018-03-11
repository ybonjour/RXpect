package ch.yvu.rxpect.mockito

import org.mockito.internal.debugging.LocationImpl

fun locationFiltered() =
    LocationImpl(PackageExcludeStackTraceFilter(listOf("ch.yvu.rxpect", "io.reactivex")))