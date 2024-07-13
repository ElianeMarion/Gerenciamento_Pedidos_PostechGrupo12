package br.com.fiap.delivery;

import br.com.fiap.delivery.controller.RouteController;
import br.com.fiap.delivery.service.RouteService;
import br.com.fiap.delivery.service.impl.RouteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class DeliveryApplicationTests {

	@Test
	void contextLoads() {
		boolean error = false;
		try {
			DeliveryApplication.main(new String[]{});
		} catch(Exception e) {
			error = true;
		}
		assertThat(error).isTrue();
	}

}
