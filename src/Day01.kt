fun main() {
    fun part1(input: List<String>): Int {
        var dial = 50
        return input.count {
            dial = if(it[0] == 'L') {
                (dial - it.drop(1).toInt()).rem(100)
            } else {
                (dial + it.drop(1).toInt()).rem(100)
            }
            dial == 0
        }
    }

    fun part2(input: List<String>): Int {
        var dial  = 50
        var count = 0
        input.forEach {
            val click = if (it[0] == 'L') -1 else 1
            repeat(it.drop(1).toInt()) {
                dial += click
                dial = dial.rem(100)
                if (dial == 0) count++
            }
        }
        return count
    }

    val input = readInput("Day01")
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 3)
    part1(input).println()
    part2(input).println()
}
