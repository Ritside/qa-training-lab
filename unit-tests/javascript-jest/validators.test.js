const { validateEmail, validatePassword } = require('./validators');

describe('validateEmail', () => {
  // Partición de equivalencia: válidos
  test.each([
    'usuario@dominio.com',
    'nombre.apellido@empresa.co',
    'test123@sub.dominio.org',
  ])('email válido: %s', (email) => {
    expect(validateEmail(email)).toBe(true);
  });

  // Partición de equivalencia: inválidos
  test.each([
    'sin-arroba.com',
    '@sindominio.com',
    'espacio @dominio.com',
    '',
    null,
  ])('email inválido: %s', (email) => {
    expect(validateEmail(email)).toBe(false);
  });
});

describe('validatePassword', () => {
  // Valores límite
  test('justo en el límite de 8 caracteres es válida', () => {
    expect(validatePassword('Abcdefg1')).toBe(true);
  });

  test('un caracter menos del límite es inválida', () => {
    expect(validatePassword('Abcdefg')).toBe(false);
  });

  test('sin mayúscula es inválida', () => {
    expect(validatePassword('abcdefg1')).toBe(false);
  });

  test('sin dígito es inválida', () => {
    expect(validatePassword('Abcdefgh')).toBe(false);
  });

  test('vacía es inválida', () => {
    expect(validatePassword('')).toBe(false);
  });
});