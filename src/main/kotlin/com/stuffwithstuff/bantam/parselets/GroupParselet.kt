package com.stuffwithstuff.bantam.parselets

import com.stuffwithstuff.bantam.Parser
import com.stuffwithstuff.bantam.Token
import com.stuffwithstuff.bantam.TokenType
import com.stuffwithstuff.bantam.expressions.Expression

/**
 * Parses parentheses used to group an expression, like "a * (b + c)".
 */
class GroupParselet : PrefixParselet {
    override fun parse(parser: Parser, token: Token): Expression {
        val expression = parser.parseExpression()
        parser.consume(TokenType.RIGHT_PAREN)
        return expression
    }
}