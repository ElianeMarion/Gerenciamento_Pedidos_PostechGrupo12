package br.com.ordertech.order.service;


import br.com.ordertech.order.dto.PixDTO;
import br.com.ordertech.order.model.Pix;
import br.com.ordertech.order.repository.PixRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PixService {

    @Autowired
    private final PixRepository pixRepository;

    @Autowired
    private final KafkaTemplate<String, PixDTO> kafkaTemplate;


    public PixDTO salvarPix(PixDTO pixDTO) {
        pixRepository.save(Pix.toEntity(pixDTO));
        kafkaTemplate.send("pix-topic", pixDTO.getIdentifier(), pixDTO);
        kafkaTemplate.send("order-topic", pixDTO.getIdentifier(), pixDTO);
        return pixDTO;
    }


}
