package com.qatraining;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorsTest {

    // --- Partición de equivalencia: emails válidos ---
    @ParameterizedTest
    @ValueSource(strings = {
            "usuario@dominio.com",
            "nombre.apellido@empresa.co",
            "test123@sub.dominio.org"
    })
    void emailValido(String email) {
        assertTrue(Validators.validateEmail(email));
    }

    // --- Partición de equivalencia: emails inválidos ---
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
            "sin-arroba.com",
            "@sindominio.com",
            "espacio @dominio.com"
    })
    void emailInvalido(String email) {
        assertFalse(Validators.validateEmail(email));
    }

    // --- Valores límite: longitud de contraseña ---
    @Test
    void passwordJustoEnElLimiteDe8Caracteres() {
        assertTrue(Validators.validatePassword("Abcdefg1"));
    }

    @Test
    void passwordUnCaracterMenosDelLimite() {
        assertFalse(Validators.validatePassword("Abcdefg"));
    }

    @Test
    void passwordSinMayuscula() {
        assertFalse(Validators.validatePassword("abcdefg1"));
    }

    @Test
    void passwordSinDigito() {
        assertFalse(Validators.validatePassword("Abcdefgh"));
    }

    @Test
    void passwordVacia() {
        assertFalse(Validators.validatePassword(""));
    }
}