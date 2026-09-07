const { By } = require('selenium-webdriver');

const BASE_URL = 'https://www.saucedemo.com';

const { buildDriver } = require('./driverFactory');

describe('Login', () => {
  let driver;

  beforeEach(async () => {
    driver = await buildDriver();
    await driver.manage().setTimeouts({ implicit: 5000 });
  });

  afterEach(async () => {
    await driver.quit();
  });

  test('login exitoso con credenciales válidas', async () => {
    await driver.get(BASE_URL);
    await driver.findElement(By.id('user-name')).sendKeys('standard_user');
    await driver.findElement(By.id('password')).sendKeys('secret_sauce');
    await driver.findElement(By.id('login-button')).click();

    const url = await driver.getCurrentUrl();
    expect(url).toContain('inventory.html');

    const title = await driver.findElement(By.className('title')).getText();
    expect(title).toBe('Products');
  }, 15000);

  test('login fallido con credenciales inválidas', async () => {
    await driver.get(BASE_URL);
    await driver.findElement(By.id('user-name')).sendKeys('usuario_invalido');
    await driver.findElement(By.id('password')).sendKeys('password_incorrecta');
    await driver.findElement(By.id('login-button')).click();

    const error = await driver.findElement(By.css("[data-test='error']")).getText();
    expect(error).toContain('Username and password do not match');
  }, 15000);

  test('login fallido con usuario bloqueado', async () => {
    await driver.get(BASE_URL);
    await driver.findElement(By.id('user-name')).sendKeys('locked_out_user');
    await driver.findElement(By.id('password')).sendKeys('secret_sauce');
    await driver.findElement(By.id('login-button')).click();

    const error = await driver.findElement(By.css("[data-test='error']")).getText();
    expect(error).toContain('Epic sadface: Sorry, this user has been locked out');
  }, 15000);
});