package com.pedrodev.pygol.scanner;

import com.pedrodev.pygol.CompilationProcess;
import com.pedrodev.pygol.constant.TokenType;
import com.pedrodev.pygol.scanner.util.LexerUtil;

import java.util.LinkedList;
import java.util.List;

import static com.pedrodev.pygol.scanner.State.*;

/**
 * @author Pedro Costa
 */
public class LexicalAnalyzer {

    public List<Token> performLexicalAnalysis(CompilationProcess compilationProcess) {

        List<Token> tokens = new LinkedList<>();
        State state = INITIAL_STATE;
        char[] characters = compilationProcess.getSourceCode().toString().toCharArray();
        StringBuilder lexeme = new StringBuilder();
        for (char c : characters) {
            switch (state) {
                case INITIAL_STATE:
                    if (Character.isWhitespace(c))
                        continue;
                    lexeme.append(c);
                    if (Character.isDigit(c)) {
                        state = INTEGER;
                    } else if (c == '"') {
                        state = LITERAL;
                    } else if (c == '#') {
                        state = COMMENT;
                    } else if (LexerUtil.isDelimiter(c)) {
                        state = DELIMITER;
                    } else if (LexerUtil.isOperator(c)) {
                        state = OPERATOR;
                    } else {
                        state = IDENTIFIER;
                    }
                    break;

                case INTEGER:
                    if (Character.isDigit(c) || c == '.') {
                        lexeme.append(c);
                        if (c == '.') {
                            state = REAL;
                        }
                    } else {
                        tokens.add(new Token(TokenType.INTEGER, lexeme.toString()));
                        lexeme.setLength(0);
                        if (!Character.isWhitespace(c))
                            lexeme.append(c);
                        if (Character.isWhitespace(c)) {
                            state = INITIAL_STATE;
                        } else if (LexerUtil.isDelimiter(c)) {
                            state = DELIMITER;
                        } else if (c == '#') {
                            state = COMMENT;
                        } else if (LexerUtil.isOperator(c)) {
                            state = OPERATOR;
                        } else if (c == '"') {
                            state = LITERAL;
                        } else {
                            state = IDENTIFIER;
                        }
                    }
                    break;

                case REAL:
                    if (Character.isDigit(c)) {
                        lexeme.append(c);
                    } else {
                        tokens.add(new Token(TokenType.REAL, lexeme.toString()));
                        lexeme.setLength(0);
                        if (!Character.isWhitespace(c))
                            lexeme.append(c);
                        if (Character.isWhitespace(c)) {
                            state = INITIAL_STATE;
                        } else if (LexerUtil.isDelimiter(c)) {
                            state = DELIMITER;
                        } else if (c == '#') {
                            state = COMMENT;
                        } else if (LexerUtil.isOperator(c)) {
                            state = OPERATOR;
                        } else if (c == '"') {
                            state = LITERAL;
                        } else {
                            state = IDENTIFIER;
                        }
                    }
                    break;

                case IDENTIFIER: // TODO implementar regra de KEYWORD
                    if (Character.isLetterOrDigit(c) || c == '_') {
                        lexeme.append(c);
                    } else {
                        tokens.add(new Token(TokenType.IDENTIFIER, lexeme.toString()));
                        lexeme.setLength(0);
                        if (!Character.isWhitespace(c))
                            lexeme.append(c);
                        if (Character.isWhitespace(c)) {
                            state = INITIAL_STATE;
                        } else if (LexerUtil.isDelimiter(c)) {
                            state = DELIMITER;
                        } else if (c == '#') {
                            state = COMMENT;
                        } else if (LexerUtil.isOperator(c)) {
                            state = OPERATOR;
                        } else if (c == '"') {
                            state = LITERAL;
                        }
                    }
                    break;

                case LITERAL:
                    lexeme.append(c);
                    if (c == '"') {
                        tokens.add(new Token(TokenType.LITERAL, lexeme.toString()));
                        lexeme.setLength(0);
                        state = INITIAL_STATE;
                    }
                    break;

                case DELIMITER:
                    tokens.add(new Token(TokenType.DELIMITER, lexeme.toString()));
                    lexeme.setLength(0);
                    if (!Character.isWhitespace(c))
                        lexeme.append(c);
                    if (Character.isWhitespace(c)) {
                        state = INITIAL_STATE;
                    } else if (c == '#') {
                        state = COMMENT;
                    } else if (LexerUtil.isOperator(c)) {
                        state = OPERATOR;
                    } else if (c == '"') {
                        state = LITERAL;
                    } else if (!LexerUtil.isDelimiter(c)) {
                        state = IDENTIFIER;
                    }
                    break;

                case OPERATOR:
                    if (LexerUtil.isOperator(c)) {
                        lexeme.append(c);
                        state = INITIAL_STATE;
                    }
                    tokens.add(new Token(TokenType.OPERATOR, lexeme.toString()));
                    lexeme.setLength(0);

                    if (LexerUtil.isOperator(c))
                        continue;
                    if (!Character.isWhitespace(c))
                        lexeme.append(c);
                    if (Character.isWhitespace(c)) {
                        state = INITIAL_STATE;
                    } else if (LexerUtil.isDelimiter(c)) {
                        state = DELIMITER;
                    } else if (c == '#') {
                        state = COMMENT;
                    } else if (c == '"') {
                        state = LITERAL;
                    } else {
                        state = IDENTIFIER;
                    }
                    break;

                case COMMENT:
                    if (c != '\n') {
                        lexeme.append(c);
                    } else {
                        state = INITIAL_STATE;
                        tokens.add(new Token(TokenType.COMMENT, lexeme.toString()));
                        lexeme.setLength(0);
                    }
                    break;
            }
        }

        return tokens;
    }

}
