fun main() {

    fun canCollect(i: Int, j: Int, input: List<List<Char>>): Boolean {
        if (input[i][j] != '@') return false
        var count = 0
        for (a in -1..1) {
            for(b in -1..1) {
                if (a == 0 && b==0) continue
                val ch = input.getOrNull(i+a)?.getOrNull(j+b)
                if (ch == '@') {
                    count++
                }
            }
        }
        return count<4
    }

    fun part1(input: List<List<Char>>): Long {
        return (0..input.lastIndex).sumOf { i ->
            (0..input.first().lastIndex).sumOf { j ->
                if (canCollect(i, j, input)) {
                    1
                } else {
                    0
                }
            }
        }.toLong()
    }

    fun part2(input: List<List<Char>>): Long {
        val mutableList = input
            .map { innerList ->
                innerList.toMutableList()
            }
            .toMutableList()
        var count = 0
        while (true) {
            var localCount = 0
            val pairs = mutableListOf<Pair<Int, Int>>()
            (0..mutableList.lastIndex).forEach { i ->
                (0..mutableList.first().lastIndex).forEach { j ->
                    if (canCollect(i, j, mutableList)) {
                        pairs.add(Pair(i, j))
                        localCount++
                    }
                }
            }
            pairs.forEach {
                mutableList[it.first][it.second] = '.'
            }
            count += localCount
            if (localCount == 0) {
                break
            }
        }
        return count.toLong()
    }

    val testInput = readInput("Day04_test").map { it.map { it } }
    part1(testInput).println()
    check(part1(testInput) == 13L)

    val input = readInput("Day04").map { it.map { it } }
    part1(input).println()
    part2(input).println()
}
