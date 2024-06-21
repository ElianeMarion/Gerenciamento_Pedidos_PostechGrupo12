package br.com.fiap.delivery.dto.route;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ConstantsTest {

    @Test
    void testInstance() {
        Assertions.assertThrows(IllegalStateException.class, Constants::new);
        Assertions.assertThrows(IllegalStateException.class, Constants.Lang::new);
        Assertions.assertThrows(IllegalStateException.class, Constants.TypePolyLine::new);
        Assertions.assertThrows(IllegalStateException.class, Constants.TypeRoute::new);
        Assertions.assertThrows(IllegalStateException.class, Constants.Units::new);
    }

}
