package com.ecommerce.imobiliaria.Exceptions.ResourceExceptionHandler;

import lombok.*;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandardError {
    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
