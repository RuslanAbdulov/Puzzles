package leaderboard


fun main(args: Array<String>) {
    println(isValidIdentifier("name"))   // true
    println(isValidIdentifier("_name"))  // true
    println(isValidIdentifier("_12"))    // true
    println(isValidIdentifier(""))       // false
    println(isValidIdentifier("012"))    // false
    println(isValidIdentifier("no$"))    // false
}
    
fun solve() : Array<String> {
    var result :Array<String> = arrayOf("")
    return result;
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
