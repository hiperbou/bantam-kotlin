package com.stuffwithstuff.bantam.parselets

import com.stuffwithstuff.bantam.Parser
import com.stuffwithstuff.bantam.Precedence
import com.stuffwithstuff.bantam.Token
import com.stuffwithstuff.bantam.TokenType
import com.stuffwithstuff.bantam.expressions.CallExpression
import com.stuffwithstuff.bantam.expressions.Expression

/**
 * Parselet to parse a function call like "a(b, c, d)".
 */
class CallParselet : InfixParselet {
    override fun parse(parser: Parser, left: Expression, token: Token): Expression {
        // Parse the comma-separated arguments until we hit, ")".
        val args = mutableListOf<Expression>()

        // There may be no arguments at all.
        if (!parser.match(TokenType.RIGHT_PAREN)) {
            do {
                args.add(parser.parseExpression())
            } while (parser.match(TokenType.COMMA))
            parser.consume(TokenType.RIGHT_PAREN)
        }
        return CallExpression(left, args)
    }

    override val precedence: Int
        get() = Precedence.CALL
}