package com.stuffwithstuff.bantam.expressions

/**
 * A simple variable name expression like "abc".
 */
class NameExpression(val name: String) : Expression {
    override fun print(builder: StringBuilder) {
        builder.append(name)
    }

}