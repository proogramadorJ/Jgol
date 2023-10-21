package com.pedrodev.pygol.scanner;

public enum State {
    INITIAL_STATE,
    INTEGER,
    REAL,
    LITERAL,
    IDENTIFIER,
    DELIMITER,
    OPERATOR,
    KEYWORD,
    COMMENT
}
