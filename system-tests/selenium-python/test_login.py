from selenium.webdriver.common.by import By

BASE_URL = "https://www.saucedemo.com"


def test_login_exitoso_con_credenciales_validas(driver):
    driver.get(BASE_URL)
    driver.find_element(By.ID, "user-name").send_keys("standard_user")
    driver.find_element(By.ID, "password").send_keys("secret_sauce")
    driver.find_element(By.ID, "login-button").click()

    assert "inventory.html" in driver.current_url
    title = driver.find_element(By.CLASS_NAME, "title")
    assert title.text == "Products"


def test_login_fallido_con_credenciales_invalidas(driver):
    driver.get(BASE_URL)
    driver.find_element(By.ID, "user-name").send_keys("usuario_invalido")
    driver.find_element(By.ID, "password").send_keys("password_incorrecta")
    driver.find_element(By.ID, "login-button").click()

    error = driver.find_element(By.CSS_SELECTOR, "[data-test='error']")
    assert "Username and password do not match" in error.text


def test_login_fallido_con_usuario_bloqueado(driver):
    driver.get(BASE_URL)
    driver.find_element(By.ID, "user-name").send_keys("locked_out_user")
    driver.find_element(By.ID, "password").send_keys("secret_sauce")
    driver.find_element(By.ID, "login-button").click()

    error = driver.find_element(By.CSS_SELECTOR, "[data-test='error']")
    assert "Epic sadface: Sorry, this user has been locked out" in error.text