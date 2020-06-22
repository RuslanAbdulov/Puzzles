package gameboard.games.game2048

/*
 * This function moves all the non-null elements to the beginning of the list
 * (by removing nulls) and merges equal elements.
 * The parameter 'merge' specifies the way how to merge equal elements:
 * it returns a new element that should be present in the resulting list
 * instead of two merged elements.
 *
 * If the function 'merge("a")' returns "aa",
 * then the function 'moveAndMergeEqual' transforms the input in the following way:
 *   a, a, b -> aa, b
 *   a, null -> a
 *   b, null, a, a -> b, aa
 *   a, a, null, a -> aa, a
 *   a, null, a, a -> aa, a
 *
 * You can find more examples in 'TestGame2048Helper'.
*/

fun <T: Any> List<T?>.moveAndMergeEqual(merge: (T) -> T): List<T> {
    val filtered = filterNotNull()
    val merged = mutableListOf<T>()
    var i = 0
    while (i < filtered.size) {
        if (i + 1 < filtered.size && filtered[i] == filtered[i + 1]) {
            merged.add(merge(filtered[i]))
            i++
        } else {
            merged.add(filtered[i])
        }
        i++
    }
    return merged

}

//fun <T : Any> List<T?>.moveAndMergeEqual(merge: (T) -> T): List<T> =
//        asSequence()
//                .filterNotNull()
//                .windowed(2, 2, true)
//                .flatMap { list -> when {
//                    list.size == 1 -> list.asSequence()
//                    list[0] != list[1] -> list.asSequence()
//                    else -> sequenceOf(merge(list[0]))
//                } }
//                .toList()
