package com.example.reactives3demo;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author DAI Yamasaki
 */
@AllArgsConstructor
public class UploadResult {
    HttpStatus status;
    String[] details;
}
