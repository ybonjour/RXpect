package ch.yvu.rxpect.mockito

inline fun <reified T : Any> defaultValue(): T? =
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
        else -> {
            null
        }
    }