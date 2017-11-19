package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {
            var current: MyDate = this@DateRange.start

            override fun hasNext(): Boolean = current <= this@DateRange.endInclusive

            override fun next(): MyDate {
                val next = current
                current = current.nextDay()
                return next
            }
        }
    }

    operator fun contains(d: MyDate): Boolean = start <= d && d <= endInclusive
}