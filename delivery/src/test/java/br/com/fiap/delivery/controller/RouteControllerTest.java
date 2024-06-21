package br.com.fiap.delivery.controller;


import br.com.fiap.delivery.service.RouteService;
import br.com.fiap.delivery.utils.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


class RouteControllerTest {

    @InjectMocks
    private RouteController routeController;

    @Mock
    private RouteService routeService;

    private MockMvc mockMvc;

    private static final String PATH = "/delivery/route";

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(routeController)
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }).build();
    }

    @Test
    void testFindRouteSuccess() throws Exception {
        when(routeService.findRoute(Mockito.any()))
                .thenReturn(ResponseEntity.ok(Util.Objects.buildRouteResponse()));
        MvcResult result = mockMvc.perform(get(PATH)
                .queryParam("addressOrigin", "rua lebrlon")
                .queryParam("addressDestination", "rua urca")
            ).andReturn();
        assertThat(result).isNotNull();
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }


}
