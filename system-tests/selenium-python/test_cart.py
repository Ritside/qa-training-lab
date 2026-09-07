import pytest
from selenium.webdriver.common.by import By
from selenium.common.exceptions import NoSuchElementException

BASE_URL = "https://www.saucedemo.com"


@pytest.fixture(autouse=True)
def login(driver):
    """Login antes de cada test de este archivo."""
    driver.get(BASE_URL)
    driver.find_element(By.ID, "user-name").send_keys("standard_user")
    driver.find_element(By.ID, "password").send_keys("secret_sauce")
    driver.find_element(By.ID, "login-button").click()


def cart_badge_exists(driver):
    try:
        driver.find_element(By.CLASS_NAME, "shopping_cart_badge")
        return True
    except NoSuchElementException:
        return False


def test_agregar_producto_actualiza_contador(driver):
    assert not cart_badge_exists(driver)

    driver.find_element(By.CSS_SELECTOR, "[data-test='add-to-cart-sauce-labs-backpack']").click()

    badge = driver.find_element(By.CLASS_NAME, "shopping_cart_badge")
    assert badge.text == "1"


def test_agregar_multiples_productos_suma_correctamente(driver):
    driver.find_element(By.CSS_SELECTOR, "[data-test='add-to-cart-sauce-labs-backpack']").click()
    driver.find_element(By.CSS_SELECTOR, "[data-test='add-to-cart-sauce-labs-bike-light']").click()

    badge = driver.find_element(By.CLASS_NAME, "shopping_cart_badge")
    assert badge.text == "2"


def test_remover_producto_actualiza_contador(driver):
    driver.find_element(By.CSS_SELECTOR, "[data-test='add-to-cart-sauce-labs-backpack']").click()
    driver.find_element(By.CSS_SELECTOR, "[data-test='remove-sauce-labs-backpack']").click()

    assert not cart_badge_exists(driver)