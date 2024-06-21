package br.com.fiap.delivery.controller.swagger;

import br.com.fiap.delivery.dto.api.ErrorApi;
import br.com.fiap.delivery.dto.route.RouteResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Consulta de rotas para entrega")
public interface RouteControllerSwagger {

    @Operation(summary = "Consulta rota entrega", description = "Consulte rota para entrega")
    @ApiResponse(
            responseCode = "200",
            content = {
                    @Content(schema = @Schema(implementation = RouteResponse.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)
            }
    )
    @ApiResponse(
            responseCode = "400",
            content = {
                    @Content(schema = @Schema(implementation = ErrorApi.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)
            }
    )
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @Parameter(name = "addressOrigin", required = true, example = "rua urca, 10 - jd são vicente, embu das artes - sp")
    @Parameter(name = "addressDestination", required = true, example = "rua teodoro sampaio, 835 - pinheirs, são paulo - sp")
    @Parameter(name = "bestRouteInRealTime", required = false)
    @Parameter(name = "typeOfTransport", required = false, example = "carro", description = "definir um dos meios de transporte: carro, caminhão, moto, bicicleta, andando")
    @Parameter(name = "avoidHighways", required = false)
    @Parameter(name = "avoidTols", required = false)
    ResponseEntity<RouteResponse> findRoute(String addressOrigin,
                                            String addressDestination,
                                            boolean bestRouteInRealTime,
                                            String typeOfTransport,
                                            boolean avoidHighways,
                                            boolean avoidTols);

}
