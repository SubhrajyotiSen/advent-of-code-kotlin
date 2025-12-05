import kotlin.math.max

fun main() {

    fun mergeRanges(input: List<String>): List<Pair<Long, Long>> {
        val ranges = input.takeWhile { it.isNotEmpty() }.map {
            val (start, end) = it.split("-")
            Pair(start.toLong(), end.toLong())
        }.sortedBy { it.first }
        val mergedRanges = mutableListOf<Pair<Long, Long>>()
        var currentRange = ranges.first()
        (1..ranges.lastIndex).forEach { i->
            val nextRange = ranges[i]
            if (currentRange.second >= nextRange.first) {
                currentRange = Pair(currentRange.first, max(currentRange.second, nextRange.second))
            } else {
                mergedRanges.add(currentRange)
                currentRange = nextRange
            }
        }
        mergedRanges.add(currentRange)
        return mergedRanges
    }
    fun part2(input: List<String>): Long {
        val mergedRanges = mergeRanges(input)
        return mergedRanges.sumOf {
            it.second - it.first + 1
        }
    }

    fun part1Simple(input: List<String>): Long {
        val ranges = mutableListOf<LongRange>()
        input.takeWhile { it.isNotEmpty() }.map {
            val start = it.split("-").first().toLong()
            val end = it.split("-").last().toLong()
            LongRange(start, end)
        }.forEach {
            ranges.add(it)
        }
        return input.takeLastWhile { it.isNotEmpty() }.map { it.toLong() }.sumOf {
            var found = false
            for (range in ranges) {
                if(range.contains(it)) {
                    found = true
                }
            }
            if (found) 1 else 0
        }.toLong()
    }

    fun part1Optimized(input: List<String>): Long {
        val mergedRanges = mergeRanges(input)

        return input.takeLastWhile { it.isNotEmpty() }.map { it.toLong() }.sumOf {
            var found = false
            for (range in mergedRanges) {
                if(it>=range.first && it<=range.second) {
                    found = true
                }
            }
            if (found) 1 else 0
        }.toLong()
    }

    val testInput = readInput("Day05_test")
    part1Simple(testInput).println()
    check(part1Simple(testInput) == 3L)
    check(part1Simple(testInput) == part1Optimized(testInput))

    val input = readInput("Day05")
    part1Simple(input).println()
    part2(input).println()
}
