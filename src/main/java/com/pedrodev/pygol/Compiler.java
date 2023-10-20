package com.pedrodev.pygol;

import com.pedrodev.pygol.constant.CompilationStatus;
import com.pedrodev.pygol.parser.Lexer;
import com.pedrodev.pygol.parser.Token;

import java.io.*;
import java.util.List;

public class Compiler {

    public CompilationProcess compile(String inputFilePath, String outPutFilePath){

        File inputFile = new File(inputFilePath);
        StringBuilder buffer = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String codeLine;
            while((codeLine = reader.readLine()) != null){
                buffer.append(codeLine);
            }
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

        Lexer lexer = new Lexer();
        List<Token> tokenList = lexer.performLexicalAnalysis(process);

        // TODO perform parser
        // TODO perform code generation


        return process;
    }

}
