import pytest
from playwright.sync_api import Page, expect

BASE_URL = "https://www.saucedemo.com"


@pytest.fixture(autouse=True)
def login(page: Page):
    """Se ejecuta automáticamente antes de cada test de este archivo."""
    page.goto(BASE_URL)
    page.fill("#user-name", "standard_user")
    page.fill("#password", "secret_sauce")
    page.click("#login-button")


def test_agregar_producto_actualiza_contador(page: Page):
    expect(page.locator(".shopping_cart_badge")).to_have_count(0)

    page.click('[data-test="add-to-cart-sauce-labs-backpack"]')

    expect(page.locator(".shopping_cart_badge")).to_have_text("1")


def test_agregar_multiples_productos_suma_correctamente(page: Page):
    page.click('[data-test="add-to-cart-sauce-labs-backpack"]')
    page.click('[data-test="add-to-cart-sauce-labs-bike-light"]')

    expect(page.locator(".shopping_cart_badge")).to_have_text("2")


def test_remover_producto_actualiza_contador(page: Page):
    page.click('[data-test="add-to-cart-sauce-labs-backpack"]')
    page.click('[data-test="remove-sauce-labs-backpack"]')

    expect(page.locator(".shopping_cart_badge")).to_have_count(0)