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

        for (char c : characters) {
            StringBuilder lexeme = new StringBuilder();

            switch (state) {
                case INITIAL_STATE:
                    if (Character.isWhitespace(c))
                        continue;
                    if (Character.isDigit(c)) {
                        state = INTEGER;
                        lexeme.append(c);
                    } else if (c == '"') {
                        state = LITERAL;
                        lexeme.append(c);
                    } else if (c == '#') {
                        state = COMMENT;
                        lexeme.append(c);
                    } else if (LexerUtil.isDelimiter(c)) {
                        state = DELIMITER;
                        lexeme.append(c);
                    } else if (LexerUtil.isOperator(c)) {
                        state = OPERATOR;
                        lexeme.append(c);
                    } else {
                        state = IDENTIFIER;
                        lexeme.append(c);
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
                        if(!Character.isWhitespace(c))
                            lexeme.append(c);
                        if (Character.isWhitespace(c)) {
                            state = INITIAL_STATE;
                        } else if (LexerUtil.isDelimiter(c)) {
                            state = DELIMITER;
                        } else if (c == '#') {
                            state = COMMENT;
                        }else if (LexerUtil.isOperator(c)) {
                            state = OPERATOR;
                        }else if(c == '"'){
                            state = LITERAL;
                        }else{
                            state = IDENTIFIER;
                        }
                    }
                        break;

                case REAL:
                    if(Character.isDigit(c)){
                        lexeme.append(c);
                    }else{

                    }
                    break;
            }
        }

        return tokens;
    }

}
