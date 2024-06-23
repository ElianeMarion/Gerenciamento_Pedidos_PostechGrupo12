package br.com.fiap.delivery.enums;

import br.com.fiap.delivery.exception.DeliveryException;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum Status {


    WAITING_SEPARATION(1, "Aguardando separação"),
    WAITING_DELIVERY(2, "Aguardando entrega"),
    IN_TRANSIT(3, "Em trânsito"),
    DELIVERY_COMPLETED(4, "Entrega concluída");

    private final Integer code;
    private final String desc;

    Status(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static int findByDesc(String desc) {
        return Stream.of(Status.values())
                .filter(s -> s.getDesc().equalsIgnoreCase(desc))
                .findFirst()
                .map(Status::getCode)
                .orElseThrow(() -> new DeliveryException("Status não localizado."));
    }

}
