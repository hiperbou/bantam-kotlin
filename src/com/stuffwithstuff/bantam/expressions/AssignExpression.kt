package com.stuffwithstuff.bantam.expressions

/**
 * An assignment expression like "a = b".
 */
class AssignExpression(private val mName: String, private val mRight: Expression) : Expression {
    override fun print(builder: StringBuilder) {
        builder.append("(").append(mName).append(" = ")
        mRight.print(builder)
        builder.append(")")
    }
}