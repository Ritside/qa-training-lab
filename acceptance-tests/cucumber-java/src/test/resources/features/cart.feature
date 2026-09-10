Feature: Shopping Cart
  Como usuario autenticado
  Quiero agregar y quitar productos del carrito
  Para preparar mi compra

  Background:
    Given el usuario inició sesión correctamente

  Scenario: Agregar un producto actualiza el contador
    When agrega el producto "sauce-labs-backpack" al carrito
    Then el contador del carrito debería mostrar "1"

  Scenario: Agregar múltiples productos suma correctamente
    When agrega el producto "sauce-labs-backpack" al carrito
    And agrega el producto "sauce-labs-bike-light" al carrito
    Then el contador del carrito debería mostrar "2"

  Scenario: Remover un producto actualiza el contador
    When agrega el producto "sauce-labs-backpack" al carrito
    And remueve el producto "sauce-labs-backpack" del carrito
    Then el contador del carrito debería estar vacío