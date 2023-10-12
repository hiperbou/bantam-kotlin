package com.stuffwithstuff.bantam

/**
 * Defines the different precedence levels used by the infix parsers. These
 * determine how a series of infix expressions will be grouped. For example,
 * "a + b * c - d" will be parsed as "(a + (b * c)) - d" because "*" has higher
 * precedence than "+" and "-". Here, bigger numbers mean higher precedence.
 */
object Precedence {
    // Ordered in increasing precedence.
    const val ASSIGNMENT = 1
    const val CONDITIONAL = 2
    const val SUM = 3
    const val PRODUCT = 4
    const val EXPONENT = 5
    const val PREFIX = 6
    const val POSTFIX = 7
    const val CALL = 8
}