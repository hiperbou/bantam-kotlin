package com.stuffwithstuff.bantam.expressions

/**
 * Interface for all expression AST node classes.
 */
interface Expression {
    /**
     * Pretty-print the expression to a string.
     */
    fun print(builder: StringBuilder)
}