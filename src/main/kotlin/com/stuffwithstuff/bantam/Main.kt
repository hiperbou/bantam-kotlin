package com.stuffwithstuff.bantam

private var sPassed = 0
private var sFailed = 0

fun main(args: Array<String>) {
    // Function call.
    test("a()", "a()")
    test("a(b)", "a(b)")
    test("a(b, c)", "a(b, c)")
    test("a(b)(c)", "a(b)(c)")
    test("a(b) + c(d)", "(a(b) + c(d))")
    test("a(b ? c : d, e + f)", "a((b ? c : d), (e + f))")

    // Unary precedence.
    test("~!-+a", "(~(!(-(+a))))")
    test("a!!!", "(((a!)!)!)")

    // Unary and binary predecence.
    test("-a * b", "((-a) * b)")
    test("!a + b", "((!a) + b)")
    test("~a ^ b", "((~a) ^ b)")
    test("-a!", "(-(a!))")
    test("!a!", "(!(a!))")

    // Binary precedence.
    test("a = b + c * d ^ e - f / g", "(a = ((b + (c * (d ^ e))) - (f / g)))")

    // Binary associativity.
    test("a = b = c", "(a = (b = c))")
    test("a + b - c", "((a + b) - c)")
    test("a * b / c", "((a * b) / c)")
    test("a ^ b ^ c", "(a ^ (b ^ c))")

    // Conditional operator.
    test("a ? b : c ? d : e", "(a ? b : (c ? d : e))")
    test("a ? b ? c : d : e", "(a ? (b ? c : d) : e)")
    test("a + b ? c * d : e / f", "((a + b) ? (c * d) : (e / f))")

    // Grouping.
    test("a + (b + c) + d", "((a + (b + c)) + d)")
    test("a ^ (b + c)", "(a ^ (b + c))")
    test("(!a)!", "((!a)!)")

    // Show the results.
    if (sFailed == 0) {
        println("Passed all " + sPassed + " tests.")
    } else {
        println("----")
        println(
            "Failed " + sFailed + " out of " +
                    (sFailed + sPassed) + " tests."
        )
    }
}

/**
 * Parses the given chunk of code and verifies that it matches the expected
 * pretty-printed result.
 */
fun test(source: String, expected: String) {
    val lexer = Lexer(source)
    val parser: Parser = BantamParser(lexer)
    try {
        val result = parser.parseExpression()
        val builder = StringBuilder()
        result.print(builder)
        val actual = builder.toString()
        if (expected == actual) {
            sPassed++
        } else {
            sFailed++
            println("[FAIL] Expected: $expected")
            println("         Actual: $actual")
        }
    } catch (ex: ParseException) {
        sFailed++
        println("[FAIL] Expected: $expected")
        println("          Error: " + ex.message)
    }
}
