package com.digitalse.cbm.back.responseFiles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RFBucketOcr {
    private Long id;
    private Long arquivo_id;
    private String texto;
}
