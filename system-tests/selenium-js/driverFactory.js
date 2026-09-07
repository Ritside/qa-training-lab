const os = require('os');
const { Builder } = require('selenium-webdriver');
const chrome = require('selenium-webdriver/chrome');

function buildDriver() {
  const options = new chrome.Options();
  options.setUserPreferences({
    'profile.password_manager_leak_detection': false,
    'credentials_enable_service': false,
    'profile.password_manager_enabled': false,
  });

  // Headless solo en Linux (Ubuntu Server no tiene interfaz gráfica)
  if (os.platform() === 'linux') {
    options.addArguments('--headless=new');
    options.addArguments('--no-sandbox');
    options.addArguments('--disable-dev-shm-usage');
  }

  return new Builder().forBrowser('chrome').setChromeOptions(options).build();
}

module.exports = { buildDriver };