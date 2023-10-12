package com.stuffwithstuff.bantam

enum class TokenType(val punctuator:Char? = null) {
    LEFT_PAREN('('),
    RIGHT_PAREN(')'),
    COMMA(','),
    ASSIGN('='),
    PLUS('+'),
    MINUS('-'),
    ASTERISK('*'),
    SLASH('/'),
    CARET('^'),
    TILDE('~'),
    BANG('!'),
    QUESTION('?'),
    COLON(':'),
    NAME,
    EOF;
}