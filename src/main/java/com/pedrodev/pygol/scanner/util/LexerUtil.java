package com.pedrodev.pygol.scanner.util;

import java.util.ArrayList;
import java.util.List;

public class LexerUtil {

    private static final List<Character> delimiters = new ArrayList<>(){{
        add('(');
        add(')');
        add('{');
        add('}');
        add(',');
        add(';');
        add('[');
        add(']');
    }};

    private static final List<Character> operators = new ArrayList<>(){{
        add('+');
        add('-');
        add('*');
        add('/');
        add('^');
        add('|');
        add('!');
        add('%');
        add('&');
        add('>');
        add('<');
        add('=');
    }};

    public static boolean isOperator(char c){
        return operators.contains(c);
    }

    public static boolean isDelimiter(char c){
        return delimiters.contains(c);
    }

}
