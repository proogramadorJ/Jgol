package com.pedrodev.pygol.scanner;

import com.pedrodev.pygol.constant.TokenType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {

    private TokenType type;
    private String value;

}
