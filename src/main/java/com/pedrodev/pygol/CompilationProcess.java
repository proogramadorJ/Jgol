package com.pedrodev.pygol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.pedrodev.pygol.constant.CompilationStatus;

import java.io.File;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompilationProcess {
    private File inputFile;
    private File outputFile;
    private StringBuilder sourceCode;
    private StringBuilder outPutSourceCode;
    private CompilationStatus status;

}
