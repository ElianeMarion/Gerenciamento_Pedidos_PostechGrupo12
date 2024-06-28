package br.com.ordertech.order.dto;

import br.com.ordertech.order.enums.PixStatus;
import br.com.ordertech.order.model.Pix;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PixDTO {
    private String identifier;
    private String chaveOrigem;
    private String chaveDestino;
    private BigDecimal valor;
    private LocalDateTime dataTransferencia;
    private PixStatus status;

    public Pix toPix(PixDTO pixDTO){
        Pix pix = new Pix();
        pix.setIdentifier(pixDTO.getIdentifier());
        pix.setStatus(pixDTO.getStatus());
        pix.setChaveDestino(pix.getChaveDestino());
        pix.setDataTransferencia(pixDTO.dataTransferencia);
        pix.setChaveOrigem(pixDTO.getChaveOrigem());
        pix.setValor(pixDTO.getValor());

        return pix;
    }


}
