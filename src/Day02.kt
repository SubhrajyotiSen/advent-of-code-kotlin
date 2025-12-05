fun main() {
    fun isInValid(num: Long): Boolean {
        val numString = num.toString()
        if (numString.length.rem(2) != 0) {
            return false
        }
        val half = numString.length/2
        val first = numString.take(half)
        val second = numString.drop(half)
        return first == second
    }

    fun part1(input: List<String>): Long {
        return input.sumOf { range ->
            val range = range.split("-")
            val start = range.first().toLong()
            val end = range.last().toLong()
            (start..end).filter {
                isInValid(it)
            }.sum()
        }
    }

    fun part2(input: List<String>): Long {
        return input.sumOf { range ->
            val range = range.split("-")
            val start = range.first().toLong()
            val end = range.last().toLong()
            (start..end).filter { num ->
                val numString = num.toString()
                val length = numString.length
                (1..length/2).firstOrNull { chunkSize ->
                    val chunks = numString.chunked(chunkSize)
                    chunks.distinct().count() == 1
                } != null
            }.sum()
        }
    }

    val testInput = readInput("Day02_test").first().split(",")
    check(part1(testInput) == 1227775554L)

    val input = readInput("Day02").first().split(",")
    part1(input).println()
    part2(input).println()
}
