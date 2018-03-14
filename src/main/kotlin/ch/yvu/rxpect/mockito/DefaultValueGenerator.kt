package ch.yvu.rxpect.mockito

import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

inline fun <reified T> defaultValueGenerator(messageIfNoDefaultValue: String): () -> T =
    {
        defaultValue()
            ?: throw IllegalStateException(messageIfNoDefaultValue)
    }

inline fun <reified T> defaultValue(): T? =
    when (T::class) {
        Unit::class -> {
            Unit as T
        }
        Byte::class -> {
            val byte: Byte = 0
            byte as T
        }
        Short::class -> {
            val short: Short = 0
            short as T
        }
        Int::class -> {
            0 as T
        }
        Long::class -> {
            0L as T
        }
        Double::class -> {
            0.0 as T
        }
        Float::class -> {
            0.0f as T
        }
        Char::class -> {
            '0' as T
        }
        String::class -> {
            "" as T
        }
        Boolean::class -> {
            false as T
        }
        List::class -> {
            emptyList<Any>() as T
        }
        Map::class -> {
            emptyMap<Any, Any>() as T
        }
        Observable::class -> {
            Observable.empty<Any>() as T
        }
        Maybe::class -> {
            Maybe.empty<Any>() as T
        }
        Single::class -> {
            Single.never<Any>() as T
        }
        else -> {
            null
        }
    }
