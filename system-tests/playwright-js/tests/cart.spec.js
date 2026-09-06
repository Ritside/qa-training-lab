const { test, expect } = require('@playwright/test');

const BASE_URL = 'https://www.saucedemo.com';

test.describe('Shopping Cart', () => {
  test.beforeEach(async ({ page }) => {
    // Login antes de cada test de esta suite
    await page.goto(BASE_URL);
    await page.fill('#user-name', 'standard_user');
    await page.fill('#password', 'secret_sauce');
    await page.click('#login-button');
  });

  test('agregar un producto actualiza el contador del carrito', async ({ page }) => {
    // El carrito debe iniciar vacío (sin badge visible)
    await expect(page.locator('.shopping_cart_badge')).toHaveCount(0);

    await page.click('[data-test="add-to-cart-sauce-labs-backpack"]');

    await expect(page.locator('.shopping_cart_badge')).toHaveText('1');
  });

  test('agregar múltiples productos suma correctamente', async ({ page }) => {
    await page.click('[data-test="add-to-cart-sauce-labs-backpack"]');
    await page.click('[data-test="add-to-cart-sauce-labs-bike-light"]');

    await expect(page.locator('.shopping_cart_badge')).toHaveText('2');
  });

  test('remover un producto actualiza el contador', async ({ page }) => {
    await page.click('[data-test="add-to-cart-sauce-labs-backpack"]');
    await page.click('[data-test="remove-sauce-labs-backpack"]');

    await expect(page.locator('.shopping_cart_badge')).toHaveCount(0);
  });
});