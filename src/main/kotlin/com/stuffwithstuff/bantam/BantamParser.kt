package com.stuffwithstuff.bantam

import com.stuffwithstuff.bantam.parselets.*

/**
 * Extends the generic Parser class with support for parsing the actual Bantam
 * grammar.
 */
class BantamParser(lexer: Lexer) : Parser(lexer) {
    init {

        // Register all of the parselets for the grammar.

        // Register the ones that need special parselets.
        register(TokenType.NAME, NameParselet())
        register(TokenType.ASSIGN, AssignParselet())
        register(TokenType.QUESTION, ConditionalParselet())
        register(TokenType.LEFT_PAREN, GroupParselet())
        register(TokenType.LEFT_PAREN, CallParselet())

        // Register the simple operator parselets.
        prefix(TokenType.PLUS, Precedence.PREFIX)
        prefix(TokenType.MINUS, Precedence.PREFIX)
        prefix(TokenType.TILDE, Precedence.PREFIX)
        prefix(TokenType.BANG, Precedence.PREFIX)

        // For kicks, we'll make "!" both prefix and postfix, kind of like ++.
        postfix(TokenType.BANG, Precedence.POSTFIX)
        infixLeft(TokenType.PLUS, Precedence.SUM)
        infixLeft(TokenType.MINUS, Precedence.SUM)
        infixLeft(TokenType.ASTERISK, Precedence.PRODUCT)
        infixLeft(TokenType.SLASH, Precedence.PRODUCT)
        infixRight(TokenType.CARET, Precedence.EXPONENT)
    }

    /**
     * Registers a postfix unary operator parselet for the given token and
     * precedence.
     */
    private fun postfix(token: TokenType, precedence: Int) {
        register(token, PostfixOperatorParselet(precedence))
    }

    /**
     * Registers a prefix unary operator parselet for the given token and
     * precedence.
     */
    private fun prefix(token: TokenType, precedence: Int) {
        register(token, PrefixOperatorParselet(precedence))
    }

    /**
     * Registers a left-associative binary operator parselet for the given token
     * and precedence.
     */
    private fun infixLeft(token: TokenType, precedence: Int) {
        register(token, BinaryOperatorParselet(precedence, false))
    }

    /**
     * Registers a right-associative binary operator parselet for the given token
     * and precedence.
     */
    private fun infixRight(token: TokenType, precedence: Int) {
        register(token, BinaryOperatorParselet(precedence, true))
    }
}