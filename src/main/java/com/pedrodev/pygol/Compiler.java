package com.pedrodev.pygol;

import com.pedrodev.pygol.constant.CompilationStatus;
import com.pedrodev.pygol.scanner.LexicalAnalyzer;
import com.pedrodev.pygol.scanner.Token;

import java.io.*;
import java.util.List;

public class Compiler {

    public CompilationProcess compile(String inputFilePath, String outPutFilePath) {

        File inputFile = new File(inputFilePath);
        StringBuilder buffer = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String codeLine;
            boolean firstLine = true;
            while ((codeLine = reader.readLine()) != null) {
                if (firstLine) {
                    buffer.append(codeLine);
                    firstLine = false;
                } else {
                    buffer.append("\n").append(codeLine);
                }


            }
            System.out.println("SOURCE CODE \n\n" + buffer.toString() + "\n\n");
        } catch (IOException e) {
            return CompilationProcess.builder()
                    .status(CompilationStatus.COMPILATION_FAILED_INPUT_FILE_NOT_ACCESSIBLE)
                    .build();
        }

        CompilationProcess process = CompilationProcess.builder()
                .inputFile(inputFile)
                .sourceCode(buffer)
                .status(CompilationStatus.COMPILATION_RUNNING)
                .build();

        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
        List<Token> tokenList = lexicalAnalyzer.performLexicalAnalysis(process);
        for (Token token : tokenList) {
            System.out.println("TokenType: " + token.getType().name() + "\n" + "TokenValue: " + token.getValue());
        }
        // TODO perform parser
        // TODO perform code generation


        return process;
    }

}
