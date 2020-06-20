package rationals

import java.math.BigInteger

//Always normalized Rational
class Rational(numerator: BigInteger, denominator: BigInteger) :Comparable<Rational> {
    private val numerator: BigInteger
    private val denominator: BigInteger

    init {
        if (denominator == BigInteger.ZERO) throw IllegalArgumentException("Denominator cannot be zero")
        val pair = normalize(numerator, denominator)
        this.numerator = pair.first
        this.denominator = pair.second
    }

    operator fun plus(other: Rational): Rational {
        val dlcm = denominator.lcm(other.denominator)
        return Rational(numerator * dlcm/denominator + other.numerator * dlcm/other.denominator, dlcm)
    }

    operator fun minus(other: Rational): Rational = plus(-other)

    operator fun unaryMinus(): Rational = Rational(-numerator, denominator)

    operator fun times(other: Rational): Rational =
            Rational(numerator * other.numerator,denominator * other.denominator)

    operator fun div(other: Rational): Rational =
            Rational(numerator * other.denominator,denominator * other.numerator)

    operator fun rangeTo(other: Rational): ClosedRange<Rational> = RationalRange(this, other)

    override fun compareTo(other: Rational): Int {
        val dlcm = denominator.lcm(other.denominator)
        return (numerator * dlcm/denominator).compareTo(other.numerator * dlcm/other.denominator)
    }

    private fun normalize(numerator: BigInteger, denominator: BigInteger): Pair<BigInteger, BigInteger> {
        val sign = if (denominator < BigInteger.ZERO) -BigInteger.ONE else BigInteger.ONE
        val gcd = numerator.gcd(denominator)
        return sign * numerator/gcd to sign * denominator/gcd
    }

    operator fun component1() = this.numerator
    operator fun component2() = this.denominator

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Rational) {
            return false
        }

        return numerator == other.numerator && denominator == other.denominator
    }

    override fun hashCode(): Int {
        var result = numerator.hashCode()
        result = 31 * result + denominator.hashCode()
        return result
    }

    override fun toString(): String {
        return if (denominator == BigInteger.ONE) "$numerator" else "$numerator/$denominator"
    }

    class RationalRange(override val start: Rational,
                        override val endInclusive: Rational) : ClosedRange<Rational>
}


fun BigInteger.lcm(other: BigInteger): BigInteger = (this * other / gcd(other)).abs()

infix fun Int.divBy(divider: Int): Rational {
    return Rational(this.toBigInteger(), divider.toBigInteger())
}

infix fun Long.divBy(divider: Long): Rational {
    return Rational(this.toBigInteger(), divider.toBigInteger())
}

infix fun BigInteger.divBy(divider: BigInteger): Rational {
    return Rational(this, divider)
}

fun String.toRational(): Rational {
    val parts = this.split("/")

    return when (parts.size) {
        1 -> Rational(parts[0].toBigInteger(), BigInteger.ONE)
        2 -> Rational(parts[0].toBigInteger(), parts[1].toBigInteger())
        else -> throw IllegalArgumentException("Malformed string")
    }

}

fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((2 divBy -4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}