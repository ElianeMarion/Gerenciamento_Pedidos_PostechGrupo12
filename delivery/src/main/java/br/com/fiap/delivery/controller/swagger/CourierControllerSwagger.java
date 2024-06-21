package br.com.fiap.delivery.controller.swagger;

import br.com.fiap.delivery.dto.CourierDto;
import br.com.fiap.delivery.dto.api.ErrorApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Gerenciamento de entregadores")
public interface CourierControllerSwagger {

    String MEDIA_TYPE = MediaType.APPLICATION_JSON_VALUE;

    @Operation(summary = "Cadastro de entregador", description = "Cadastre um entregador")
    @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = CourierDto.class), mediaType = MEDIA_TYPE)})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorApi.class), mediaType = MEDIA_TYPE)})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    ResponseEntity<CourierDto> save(CourierDto courierDto);

    @Operation(summary = "Lista entregadores", description = "Liste os entregadores")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = List.class), mediaType = MEDIA_TYPE)})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorApi.class), mediaType = MEDIA_TYPE)})
    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    ResponseEntity<List<CourierDto>> findAll();

    @Operation(summary = "Atualiza dados do entregador", description = "Atualize dados de entregador")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = List.class), mediaType = MEDIA_TYPE)})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorApi.class), mediaType = MEDIA_TYPE)})
    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    ResponseEntity<CourierDto> updateStatus(String courierID, CourierDto courierDto);

}
