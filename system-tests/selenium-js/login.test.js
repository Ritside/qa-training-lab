const { Builder, By } = require('selenium-webdriver');
const chrome = require('selenium-webdriver/chrome');

const BASE_URL = 'https://www.saucedemo.com';

function buildDriver() {
  const options = new chrome.Options();
  // Mismo fix que en Java y Python: deshabilitar detección de contraseñas
  // filtradas y gestor de contraseñas, que interfieren con la automatización
  options.setUserPreferences({
    'profile.password_manager_leak_detection': false,
    'credentials_enable_service': false,
    'profile.password_manager_enabled': false,
  });

  return new Builder().forBrowser('chrome').setChromeOptions(options).build();
}

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