package com.stuffwithstuff.bantam.parselets

import com.stuffwithstuff.bantam.Parser
import com.stuffwithstuff.bantam.Token
import com.stuffwithstuff.bantam.expressions.Expression
import com.stuffwithstuff.bantam.expressions.PostfixExpression

/**
 * Generic infix parselet for an unary arithmetic operator. Parses postfix
 * unary "?" expressions.
 */
class PostfixOperatorParselet(override val precedence: Int) : InfixParselet {
    override fun parse(parser: Parser, left: Expression, token: Token): Expression {
        return PostfixExpression(left, token.type)
    }

}