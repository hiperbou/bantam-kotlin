package com.stuffwithstuff.bantam.parselets

import com.stuffwithstuff.bantam.Parser
import com.stuffwithstuff.bantam.Precedence
import com.stuffwithstuff.bantam.Token
import com.stuffwithstuff.bantam.TokenType
import com.stuffwithstuff.bantam.expressions.ConditionalExpression
import com.stuffwithstuff.bantam.expressions.Expression

/**
 * Parselet for the condition or "ternary" operator, like "a ? b : c".
 */
class ConditionalParselet : InfixParselet {
    override fun parse(parser: Parser, left: Expression, token: Token): Expression {
        val thenArm = parser.parseExpression()
        parser.consume(TokenType.COLON)
        val elseArm = parser.parseExpression(Precedence.CONDITIONAL - 1)
        return ConditionalExpression(left, thenArm, elseArm)
    }

    override val precedence: Int
        get() = Precedence.CONDITIONAL
}