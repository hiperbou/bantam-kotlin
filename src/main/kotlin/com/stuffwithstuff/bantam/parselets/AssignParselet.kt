package com.stuffwithstuff.bantam.parselets

import com.stuffwithstuff.bantam.ParseException
import com.stuffwithstuff.bantam.Parser
import com.stuffwithstuff.bantam.Precedence
import com.stuffwithstuff.bantam.Token
import com.stuffwithstuff.bantam.expressions.AssignExpression
import com.stuffwithstuff.bantam.expressions.Expression
import com.stuffwithstuff.bantam.expressions.NameExpression

/**
 * Parses assignment expressions like "a = b". The left side of an assignment
 * expression must be a simple name like "a", and expressions are
 * right-associative. (In other words, "a = b = c" is parsed as "a = (b = c)").
 */
class AssignParselet : InfixParselet {
    override fun parse(parser: Parser, left: Expression, token: Token): Expression {
        val right = parser.parseExpression(Precedence.ASSIGNMENT - 1)
        if (left !is NameExpression) throw ParseException(
            "The left-hand side of an assignment must be a name."
        )
        val name = left.name
        return AssignExpression(name, right)
    }

    override val precedence: Int
        get() = Precedence.ASSIGNMENT
}