package br.com.fiap.delivery.controller.swagger;

import br.com.fiap.delivery.dto.api.ErrorApi;
import br.com.fiap.delivery.dto.OrderDelivery;
import br.com.fiap.delivery.dto.OrderDeliveryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Gerenciamento de entregas")
public interface DeliveryControllerSwagger {


    String MEDIA_TYPE = MediaType.APPLICATION_JSON_VALUE;

    @Operation(summary = "Cadastra nova entrega", description = "Cadastre uma entrega")
    @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = OrderDeliveryResponse.class), mediaType = MEDIA_TYPE)})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorApi.class), mediaType = MEDIA_TYPE)})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    ResponseEntity<OrderDeliveryResponse> saveDelivery(OrderDelivery orderDelivery);


    @Operation(summary = "Busca entregas por subsetor", description = "Busque um subsetor")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = List.class), mediaType = MEDIA_TYPE)})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorApi.class), mediaType = MEDIA_TYPE)})
    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    ResponseEntity<List<OrderDeliveryResponse>> findDeliveryWhenWaitingDelivery(Integer subSector);


    @Operation(summary = "Atualiza entrega com informações do entregador", description = "Atualize entrega com informações do entregador")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = List.class), mediaType = MEDIA_TYPE)})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorApi.class), mediaType = MEDIA_TYPE)})
    @ApiResponse(responseCode = "409", content = {@Content(schema = @Schema(implementation = ErrorApi.class), mediaType = MEDIA_TYPE)})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    ResponseEntity<OrderDeliveryResponse> updateSetCourier(String deliveryID, String courierID);


    @Operation(summary = "Atualiza status da entrega para em trânsito", description = "Atualize o status da entrega para em trânsito")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = List.class), mediaType = MEDIA_TYPE)})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorApi.class), mediaType = MEDIA_TYPE)})
    @ApiResponse(responseCode = "409", content = {@Content(schema = @Schema(implementation = ErrorApi.class), mediaType = MEDIA_TYPE)})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    ResponseEntity<List<OrderDeliveryResponse>> updateDeliveryLeft(String courierID);


    @Operation(summary = "Atualiza status da entrega para concluída", description = "Atualize o status da entrega para concluída")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = List.class), mediaType = MEDIA_TYPE)})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorApi.class), mediaType = MEDIA_TYPE)})
    @ApiResponse(responseCode = "409", content = {@Content(schema = @Schema(implementation = ErrorApi.class), mediaType = MEDIA_TYPE)})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    ResponseEntity<OrderDeliveryResponse> updateDeliveryCompleted(String deliveryID);
}
