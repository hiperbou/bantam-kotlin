package com.stuffwithstuff.bantam

/**
 * A very primitive lexer. Takes a string and splits it into a series of
 * Tokens. Operators and punctuation are mapped to unique keywords. Names,
 * which can be any series of letters, are turned into NAME tokens. All other
 * characters are ignored (except to separate names). Numbers and strings are
 * not supported. This is really just the bare minimum to give the parser
 * something to work with.
 */
class Lexer(private val text: String) : MutableIterator<Token> {
    override fun hasNext(): Boolean {
        return true
    }

    override fun next(): Token {
        while (index < text.length) {
            val c = text[index++]
            if (punctuators.containsKey(c)) {
                // Handle punctuation.
                return Token(punctuators.getValue(c), c.toString())
            } else if (Character.isLetter(c)) {
                // Handle names.
                val start = index - 1
                while (index < text.length) {
                    if (!Character.isLetter(text[index])) break
                    index++
                }
                val name = text.substring(start, index)
                return Token(TokenType.NAME, name)
            } else {
                // Ignore all other characters (whitespace, etc.)
            }
        }

        // Once we've reached the end of the string, just return EOF tokens. We'll
        // just keeping returning them as many times as we're asked so that the
        // parser's lookahead doesn't have to worry about running out of tokens.
        return Token(TokenType.EOF, "")
    }

    override fun remove() {
        throw UnsupportedOperationException()
    }

    private val punctuators = TokenType.values().filter { it.punctuator != null }.associateBy { it.punctuator!! }
    private var index = 0
}