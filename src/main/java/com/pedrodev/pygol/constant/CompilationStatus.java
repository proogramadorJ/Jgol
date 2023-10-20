package com.pedrodev.pygol.constant;

import lombok.Getter;

@Getter
public enum CompilationStatus {

    COMPILATION_RUNNING("Compilação iniciada"),
    COMPILATION_FINISHED_OK("Compilação finalizada com sucesso"),
    COMPILATION_FAILED_WITH_ERRORS("Compilação falhou com erros"),
    COMPILATION_FAILED_INPUT_FILE_NOT_ACCESSIBLE("Compilação falhou, arquivo fonte não encontrado ou sem permissão de leitura");

    private final String value;

   private CompilationStatus(String value){
        this.value = value;
    }
}
