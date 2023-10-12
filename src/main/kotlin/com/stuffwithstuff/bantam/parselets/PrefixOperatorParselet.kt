package com.stuffwithstuff.bantam.parselets

import com.stuffwithstuff.bantam.Parser
import com.stuffwithstuff.bantam.Token
import com.stuffwithstuff.bantam.expressions.Expression
import com.stuffwithstuff.bantam.expressions.PrefixExpression

/**
 * Generic prefix parselet for an unary arithmetic operator. Parses prefix
 * unary "-", "+", "~", and "!" expressions.
 */
class PrefixOperatorParselet(val precedence: Int) : PrefixParselet {
    override fun parse(parser: Parser, token: Token): Expression {
        // To handle right-associative operators like "^", we allow a slightly
        // lower precedence when parsing the right-hand side. This will let a
        // parselet with the same precedence appear on the right, which will then
        // take *this* parselet's result as its left-hand argument.
        val right = parser.parseExpression(precedence)
        return PrefixExpression(token.type, right)
    }

}