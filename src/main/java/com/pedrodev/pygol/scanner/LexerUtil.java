package com.pedrodev.pygol.scanner;

import java.util.ArrayList;
import java.util.List;

public class LexerUtil {

    private static final List<Character> delimiters = new ArrayList<>() {{
        add('(');
        add(')');
        add('{');
        add('}');
        add(',');
        add(';');
        add('[');
        add(']');
    }};

    private static final List<Character> operators = new ArrayList<>() {{
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

    private static final List<String> keywords = new ArrayList<>() {{
        add("int");
        add("double");
        add("string");
        add("function");
        add("const");
        add("if");
        add("else");
        add("while");
        add("for");
        add("break");
        add("case");
        add("switch");
        add("return");
        add("break");
    }};

    public static boolean isKeyword(String keyword) {
        return keywords.contains(keyword);
    }

    public static boolean isOperator(char c) {
        return operators.contains(c);
    }

    public static boolean isDelimiter(char c) {
        return delimiters.contains(c);
    }

}
