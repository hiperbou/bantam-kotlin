package com.stuffwithstuff.bantam.expressions

import com.stuffwithstuff.bantam.TokenType

/**
 * A binary arithmetic expression like "a + b" or "c ^ d".
 */
class OperatorExpression(
    private val mLeft: Expression,
    private val mOperator: TokenType,
    private val mRight: Expression
) : Expression {
    override fun print(builder: StringBuilder) {
        builder.append("(")
        mLeft.print(builder)
        builder.append(" ").append(mOperator.punctuator).append(" ")
        mRight.print(builder)
        builder.append(")")
    }
}