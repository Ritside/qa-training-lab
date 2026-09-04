import pytest
from validators import validate_email, validate_password


# --- Partición de equivalencia: emails válidos ---
@pytest.mark.parametrize("email", [
    "usuario@dominio.com",
    "nombre.apellido@empresa.co",
    "test123@sub.dominio.org",
])
def test_email_valido(email):
    assert validate_email(email) is True


# --- Partición de equivalencia: emails inválidos ---
@pytest.mark.parametrize("email", [
    "sin-arroba.com",
    "@sindominio.com",
    "espacio @dominio.com",
    "",
    None,
])
def test_email_invalido(email):
    assert validate_email(email) is False


# --- Valores límite: longitud de contraseña ---
def test_password_justo_en_el_limite_8_caracteres():
    assert validate_password("Abcdefg1") is True  # exactamente 8, válida

def test_password_un_caracter_menos_del_limite():
    assert validate_password("Abcdefg") is False  # 7 caracteres, inválida

def test_password_sin_mayuscula():
    assert validate_password("abcdefg1") is False

def test_password_sin_digito():
    assert validate_password("Abcdefgh") is False

def test_password_vacia():
    assert validate_password("") is False