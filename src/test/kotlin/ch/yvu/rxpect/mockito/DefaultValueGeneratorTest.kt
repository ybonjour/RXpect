package ch.yvu.rxpect.mockito

import io.reactivex.Maybe
import io.reactivex.Observable
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class DefaultValueGeneratorTest {
    @Test
    fun generatesUnit() {
        val value = defaultValue<Unit>()

        assertThat(value, `is`(Unit))
    }

    @Test
    fun generatesByte() {
        val value = defaultValue<Byte>()
        val byte: Byte = 0

        assertThat(value, `is`(byte))
    }

    @Test
    fun generateShort() {
        val value = defaultValue<Short>()
        val short: Short = 0

        assertThat(value, `is`(short))
    }

    @Test
    fun generatesInt() {
        val value = defaultValue<Int>()

        assertThat(value, `is`(0))
    }

    @Test
    fun generatesLong() {
        val value = defaultValue<Long>()

        assertThat(value, `is`(0L))
    }

    @Test
    fun generatesDouble() {
        val value = defaultValue<Double>()

        assertThat(value, `is`(0.0))
    }

    @Test
    fun generatesFloat() {
        val value = defaultValue<Float>()

        assertThat(value, `is`(0.0f))
    }

    @Test
    fun generatesChar() {
        val value = defaultValue<Char>()

        assertThat(value, `is`('0'))
    }

    @Test
    fun generatesString() {
        val value = defaultValue<String>()

        assertThat(value, `is`(""))
    }

    @Test
    fun generatesBoolean() {
        val value = defaultValue<Boolean>()

        assertThat(value, `is`(false))
    }

    @Test
    fun generatesList() {
        val value = defaultValue<List<String>>()

        assertThat(value, `is`(emptyList()))
    }

    @Test
    fun generatesMap() {
        val value = defaultValue<Map<String, String>>()

        assertThat(value, `is`(emptyMap()))
    }

    @Test
    fun generatesObservable() {
        val value = defaultValue<Observable<Int>>()

        val testObserver = value?.test()

        testObserver!!.assertNoErrors()
        testObserver.assertNoValues()
    }

    @Test
    fun generatesMaybe() {
        val value = defaultValue<Maybe<Int>>()

        val testObserver = value?.test()

        testObserver!!.assertNoErrors()
        testObserver.assertNoValues()
    }

    @Test
    fun returnsNullForUnknwonType() {
        val value = defaultValue<Runnable>()

        assertThat(value, nullValue())
    }
}