package sandbox

fun List<Int>.sum(): Int {
    var result = 0
    for (i in this) {
        result += i
    }
    return result
}

fun main(args: Array<String>) {
    val sum = listOf(1, 2, 3).sum()
    println(sum)    // 6
}

fun isValidIdentifier(s: String): Boolean {
    if (s.isEmpty()) {
        return false
    }
    if (!s[0].isLetter() && s[0] != '_') {
        return false
    }

    for (c in s) {
        if (!c.isLetterOrDigit() && c != '_') {
            return false
        }
    }

    return true
}
