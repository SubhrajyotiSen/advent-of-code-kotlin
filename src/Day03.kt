fun main() {

    fun findMaxJoltage(
        remainingBank: String,
        batteryRemaining: Int, 
        str: String
    ): Long {
        if (batteryRemaining == 0) {
            return str.toLong()
        }

        var max = 0
        return remainingBank.dropLast(batteryRemaining - 1).map { it.digitToInt() }.mapIndexed { index, ch ->
            if (ch > max) {
                max = ch
                findMaxJoltage(
                    remainingBank.substring(index + 1),
                    batteryRemaining - 1,
                    "$str$ch"
                )
            } else {
                0
            }
        }.max()
    }

    fun part1Simple(input: List<String>): Long {
        return input.sumOf { bank ->
            var max = -1
            var maxIndex = -1
            val voltageList = bank.map { it.digitToInt() }
            voltageList.dropLast(1).forEachIndexed { index, voltage ->
                if(voltage > max) {
                    max = voltage
                    maxIndex = index
                }
            }
            max = -1
            voltageList.drop(maxIndex.plus(1)).forEach { voltage ->
                if (voltage > max) {
                    max = voltage
                }
            }
            voltageList[maxIndex].toLong() * 10 + max
        }
    }

    fun part1Reused(input: List<String>): Long {
        return input.sumOf { findMaxJoltage(it, 2, "") }
    }

    fun part2(input: List<String>): Long {
        return input.sumOf { findMaxJoltage(it, 12, "") }
    }

    val testInput = readInput("Day03_test")
    part1Simple(testInput).println()
    check(part1Simple(testInput) == 357L)
    check(part1Reused(testInput) == 357L)

    val input = readInput("Day03")
    part1Simple(input).println()
    part2(input).println()
}
