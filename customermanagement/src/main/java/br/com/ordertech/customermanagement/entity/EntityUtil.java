package br.com.ordertech.customermanagement.entity;

import br.com.ordertech.customermanagement.entity.customer.Address;
import br.com.ordertech.customermanagement.entity.customer.exception.CustomerInvalidException;

import java.util.Optional;

public class EntityUtil {
    public static String isNullOrBlank(String value, String message) {
        return Optional.ofNullable(value)
                .filter(v -> !v.isBlank())
                .orElseThrow(() -> new CustomerInvalidException(message));
    }

    public static Integer isNull(Integer value, String message) {
        return (Integer) execute(value, message);
    }

    public static Long isNull(Long value, String message) {
        return (Long) execute(value, message);
    }

    public static Address isNull(Address value, String message) {
        return (Address) execute(value, message);
    }

    private static Object execute(Object value, String message) {
        return Optional.ofNullable(value)
                .orElseThrow(() -> new CustomerInvalidException(message));
    }
}
