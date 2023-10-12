package com.stuffwithstuff.bantam.expressions

import com.stuffwithstuff.bantam.TokenType

/**
 * A postfix unary arithmetic expression like "a!".
 */
class PostfixExpression(private val mLeft: Expression, private val mOperator: TokenType) : Expression {
    override fun print(builder: StringBuilder) {
        builder.append("(")
        mLeft.print(builder)
        builder.append(mOperator.punctuator).append(")")
    }
}