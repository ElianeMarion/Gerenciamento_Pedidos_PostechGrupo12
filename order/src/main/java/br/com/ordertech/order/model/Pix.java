package br.com.ordertech.order.model;

import br.com.ordertech.order.dto.PixDTO;
import br.com.ordertech.order.enums.PixStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document("pix")
@Getter
@Setter
public class Pix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String identifier;
    private String chaveOrigem;
    private String chaveDestino;
    private BigDecimal valor;
    private LocalDateTime dataTransferencia;
    @Enumerated(EnumType.STRING)
    private PixStatus status;

    public static Pix toEntity(PixDTO pixDTO) {
        Pix pix = new Pix();
        pix.setIdentifier(pixDTO.getIdentifier());
        pix.setChaveDestino(pixDTO.getChaveDestino());
        pix.setStatus(pixDTO.getStatus());
        pix.setValor(pixDTO.getValor());
        pix.setDataTransferencia(pixDTO.getDataTransferencia());
        pix.setChaveOrigem(pixDTO.getChaveOrigem());
        return pix;
    }
}
