package br.com.fiap.delivery.service.impl;

import br.com.fiap.delivery.dto.CourierDto;
import br.com.fiap.delivery.enums.CourierStatus;
import br.com.fiap.delivery.repository.CourierRepository;
import br.com.fiap.delivery.utils.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class CourierServiceImplTest {

    @InjectMocks
    private CourierServiceImpl courierServiceImpl;

    @Mock
    private CourierRepository courierRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateStatus() {
        when(courierRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(Util.Objects.buildCourierSaved()));
        ResponseEntity<CourierDto> resp = courierServiceImpl
                .updateStatus("2367", CourierStatus.FREE.getDesc());
        assertThat(resp).isNotNull();
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(courierRepository, never()).save(Mockito.any());
    }


}
