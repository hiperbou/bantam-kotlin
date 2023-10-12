package com.stuffwithstuff.bantam.expressions

import com.stuffwithstuff.bantam.TokenType

/**
 * A prefix unary arithmetic expression like "!a" or "-b".
 */
class PrefixExpression(private val mOperator: TokenType, private val mRight: Expression) : Expression {
    override fun print(builder: StringBuilder) {
        builder.append("(").append(mOperator.punctuator)
        mRight.print(builder)
        builder.append(")")
    }
}