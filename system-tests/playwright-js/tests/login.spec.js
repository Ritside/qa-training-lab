const { test, expect } = require('@playwright/test');

const BASE_URL = 'https://www.saucedemo.com';

test.describe('Login', () => {
  test('login exitoso con credenciales válidas', async ({ page }) => {
    await page.goto(BASE_URL);
    await page.fill('#user-name', 'standard_user');
    await page.fill('#password', 'secret_sauce');
    await page.click('#login-button');

    // Verifica que llegamos a la página de inventario
    await expect(page).toHaveURL(/.*inventory.html/);
    await expect(page.locator('.title')).toHaveText('Products');
  });

  test('login fallido con credenciales inválidas', async ({ page }) => {
    await page.goto(BASE_URL);
    await page.fill('#user-name', 'usuario_invalido');
    await page.fill('#password', 'password_incorrecta');
    await page.click('#login-button');

    // Verifica que se muestra el mensaje de error, sin redirigir
    await expect(page).toHaveURL(BASE_URL + '/');
    await expect(page.locator('[data-test="error"]'))
      .toContainText('Username and password do not match');
  });

  test('login fallido con usuario bloqueado', async ({ page }) => {
    await page.goto(BASE_URL);
    await page.fill('#user-name', 'locked_out_user');
    await page.fill('#password', 'secret_sauce');
    await page.click('#login-button');

    await expect(page.locator('[data-test="error"]'))
      .toContainText('Epic sadface: Sorry, this user has been locked out.');
  });
});