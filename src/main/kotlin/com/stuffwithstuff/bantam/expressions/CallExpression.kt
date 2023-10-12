package com.stuffwithstuff.bantam.expressions

/**
 * A function call like "a(b, c, d)".
 */
class CallExpression(private val mFunction: Expression, private val mArgs: List<Expression>) : Expression {
    override fun print(builder: StringBuilder) {
        mFunction.print(builder)
        builder.append("(")
        mArgs.forEachIndexed { i, argument ->
            argument.print(builder)
            if (i < mArgs.lastIndex) builder.append(", ")
        }
        builder.append(")")
    }
}