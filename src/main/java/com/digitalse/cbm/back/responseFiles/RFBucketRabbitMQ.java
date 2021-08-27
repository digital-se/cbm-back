package com.digitalse.cbm.back.responseFiles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RFBucketRabbitMQ {

    private Long id;
    private String mime;
    private Long tamanho;
    private byte[] dados;

}
