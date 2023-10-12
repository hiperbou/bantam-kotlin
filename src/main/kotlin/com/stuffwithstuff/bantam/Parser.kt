package com.stuffwithstuff.bantam

import com.stuffwithstuff.bantam.expressions.Expression
import com.stuffwithstuff.bantam.parselets.InfixParselet
import com.stuffwithstuff.bantam.parselets.PrefixParselet

open class Parser(private val tokens: Iterator<Token>) {
    fun register(token: TokenType, parselet: PrefixParselet) {
        prefixParselets[token] = parselet
    }

    fun register(token: TokenType, parselet: InfixParselet) {
        infixParselets[token] = parselet
    }

    fun parseExpression(precedence: Int = 0): Expression {
        var token = consume()
        val prefixParselet = prefixParselets[token.type]
            ?: throw ParseException(
                "Could not parse \"" +
                        token.text + "\"."
            )
        var left = prefixParselet.parse(this, token)
        while (precedence < this.precedence) {
            token = consume()
            val infixParselet = infixParselets[token.type]
                ?: throw ParseException(
                    "Could not parse \"" +
                            token.text + "\"."
                )
            left = infixParselet.parse(this, left, token)
        }
        return left
    }

    fun match(expected: TokenType): Boolean {
        val token = lookAhead(0)
        if (token.type != expected) {
            return false
        }
        consume()
        return true
    }

    fun consume(expected: TokenType): Token {
        val token = lookAhead(0)
        if (token.type != expected) {
            throw RuntimeException("Expected token $expected and found ${token.type}")
        }
        return consume()
    }

    private fun consume(): Token {
        // Make sure we've read the token.
        lookAhead(0)
        return tokensRead.removeAt(0)
    }

    private fun lookAhead(distance: Int): Token {
        // Read in as many as needed.
        while (distance >= tokensRead.size) {
            tokensRead.add(tokens.next())
        }

        // Get the queued token.
        return tokensRead[distance]
    }

    private val precedence: Int
        get() {
            val parser = infixParselets[lookAhead(0).type]
            return parser?.precedence ?: 0
        }
    private val tokensRead = mutableListOf<Token>()
    private val prefixParselets = mutableMapOf<TokenType, PrefixParselet>()
    private val infixParselets = mutableMapOf<TokenType, InfixParselet>()
}