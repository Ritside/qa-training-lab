const { By } = require('selenium-webdriver');

const BASE_URL = 'https://www.saucedemo.com';

const { buildDriver } = require('./driverFactory');

async function cartBadgeExists(driver) {
  const badges = await driver.findElements(By.className('shopping_cart_badge'));
  return badges.length > 0;
}

describe('Shopping Cart', () => {
  let driver;

  beforeEach(async () => {
    driver = await buildDriver();
    await driver.manage().setTimeouts({ implicit: 5000 });

    await driver.get(BASE_URL);
    await driver.findElement(By.id('user-name')).sendKeys('standard_user');
    await driver.findElement(By.id('password')).sendKeys('secret_sauce');
    await driver.findElement(By.id('login-button')).click();
  });

  afterEach(async () => {
    await driver.quit();
  });

  test('agregar producto actualiza el contador', async () => {
    expect(await cartBadgeExists(driver)).toBe(false);

    await driver.findElement(By.css("[data-test='add-to-cart-sauce-labs-backpack']")).click();

    const badge = await driver.findElement(By.className('shopping_cart_badge')).getText();
    expect(badge).toBe('1');
  }, 15000);

  test('agregar múltiples productos suma correctamente', async () => {
    await driver.findElement(By.css("[data-test='add-to-cart-sauce-labs-backpack']")).click();
    await driver.findElement(By.css("[data-test='add-to-cart-sauce-labs-bike-light']")).click();

    const badge = await driver.findElement(By.className('shopping_cart_badge')).getText();
    expect(badge).toBe('2');
  }, 15000);

  test('remover producto actualiza el contador', async () => {
    await driver.findElement(By.css("[data-test='add-to-cart-sauce-labs-backpack']")).click();
    await driver.findElement(By.css("[data-test='remove-sauce-labs-backpack']")).click();

    expect(await cartBadgeExists(driver)).toBe(false);
  }, 15000);
});