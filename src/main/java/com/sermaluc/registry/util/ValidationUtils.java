package com.sermaluc.registry.util;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidationUtils {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private ValidationUtils() {
        throw new UnsupportedOperationException("Esta clase no debe ser instanciada");
    }

    public static boolean isValidRut(String rut) {
        if (!StringUtils.hasText(rut)) {
            return false;
        }

        // Normalizar: quitar puntos, espacios y convertir a mayúsculas
        String clean = rut.replace(".", "").replace(" ", "").toUpperCase();

        // Asegurar que exista guion separador; si no, considerar último carácter como DV
        if (!clean.contains("-")) {
            if (clean.length() < 2) {
                return false;
            }
            clean = clean.substring(0, clean.length() - 1) + "-" + clean.substring(clean.length() - 1);
        }

        String[] parts = clean.split("-");
        if (parts.length != 2) {
            return false;
        }

        String number = parts[0];
        String dv = parts[1];

        if (number.isEmpty() || dv.isEmpty() || dv.length() != 1) {
            return false;
        }

        // Número debe ser dígitos
        if (!number.chars().allMatch(Character::isDigit)) {
            return false;
        }

        char expectedDv = calculateRutDv(number);
        char providedDv = dv.charAt(0);

        return providedDv == expectedDv;
    }

    private static char calculateRutDv(String number) {
        int sum = 0;
        int factor = 2;
        for (int i = number.length() - 1; i >= 0; i--) {
            sum += Character.getNumericValue(number.charAt(i)) * factor;
            factor++;
            if (factor > 7) {
                factor = 2;
            }
        }
        int mod = 11 - (sum % 11);
        if (mod == 11) {
            return '0';
        } else if (mod == 10) {
            return 'K';
        } else {
            return Character.forDigit(mod, 10);
        }
    }

    public static boolean isValidName(String name) {
        return StringUtils.hasText(name) && name.length() <= 50;
    }

    public static boolean isValidSurname(String surname) {
        return StringUtils.hasText(surname) && surname.length() <= 50;
    }

    public static boolean isValidBirthDate(String birthDate) {
        try {
            LocalDate.parse(birthDate, DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidAddress(String address) {
        return StringUtils.hasText(address) && address.length() <= 100;
    }

    public static boolean isValidStreet(String street) {
        return StringUtils.hasText(street) && street.length() <= 100;
    }

    public static boolean isValidComuna(String comuna) {
        return StringUtils.hasText(comuna) && comuna.length() <= 50;
    }

    public static boolean isValidRegion(String region) {
        return StringUtils.hasText(region) && region.length() <= 50;
    }
}