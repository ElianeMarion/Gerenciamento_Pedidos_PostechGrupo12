package br.com.fiap.delivery.enums;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum CourierStatus {

    FREE(1,"livre"),
    BUSY(2,"ocupado"),
    INACTIVE(3,"inativo");


    private final Integer code;
    private final String desc;

    CourierStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
