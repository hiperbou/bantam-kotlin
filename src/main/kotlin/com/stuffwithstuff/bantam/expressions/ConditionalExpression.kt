package com.stuffwithstuff.bantam.expressions

/**
 * A ternary conditional expression like "a ? b : c".
 */
class ConditionalExpression(
    private val mCondition: Expression, private val mThenArm: Expression, private val mElseArm: Expression
) : Expression {
    override fun print(builder: StringBuilder) {
        builder.append("(")
        mCondition.print(builder)
        builder.append(" ? ")
        mThenArm.print(builder)
        builder.append(" : ")
        mElseArm.print(builder)
        builder.append(")")
    }
}