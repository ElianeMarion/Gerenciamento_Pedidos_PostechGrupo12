package br.com.ordertech.order.enums;

public enum StatusEnum {

    WAITING_SEPARATION(1, "Aguardando separação"),
    WAITING_DELIVERY(2, "Aguardando entrega"),
    IN_TRANSIT(3, "Em trânsito"),
    DELIVERY_COMPLETED(4, "Entrega concluída");

    private final Integer code;
    private final String desc;

    StatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static StatusEnum fromCode(Integer codeStatus) {
        for (StatusEnum status : StatusEnum.values()) {
            if (status.code.equals(codeStatus)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código de status inválido: " + codeStatus);
    }
}
