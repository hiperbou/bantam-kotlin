package com.stuffwithstuff.bantam.parselets

import com.stuffwithstuff.bantam.Parser
import com.stuffwithstuff.bantam.Token
import com.stuffwithstuff.bantam.expressions.Expression
import com.stuffwithstuff.bantam.expressions.NameExpression

/**
 * Simple parselet for a named variable like "abc".
 */
class NameParselet : PrefixParselet {
    override fun parse(parser: Parser, token: Token): Expression {
        return NameExpression(token.text)
    }
}