package com.stuffwithstuff.bantam.parselets

import com.stuffwithstuff.bantam.Parser
import com.stuffwithstuff.bantam.Token
import com.stuffwithstuff.bantam.expressions.Expression
import com.stuffwithstuff.bantam.expressions.OperatorExpression

/**
 * Generic infix parselet for a binary arithmetic operator. The only
 * difference when parsing, "+", "-", "*", "/", and "^" is precedence and
 * associativity, so we can use a single parselet class for all of those.
 */
class BinaryOperatorParselet(override val precedence: Int, private val mIsRight: Boolean) : InfixParselet {
    override fun parse(parser: Parser, left: Expression, token: Token): Expression {
        // To handle right-associative operators like "^", we allow a slightly
        // lower precedence when parsing the right-hand side. This will let a
        // parselet with the same precedence appear on the right, which will then
        // take *this* parselet's result as its left-hand argument.
        val right = parser.parseExpression(
            precedence - if (mIsRight) 1 else 0
        )
        return OperatorExpression(left, token.type, right)
    }
}