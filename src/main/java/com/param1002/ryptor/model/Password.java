package com.param1002.ryptor.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Password {

    private String id;

    private String name;

    private String value;

}

