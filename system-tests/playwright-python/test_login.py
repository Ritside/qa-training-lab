import re
from playwright.sync_api import Page, expect

BASE_URL = "https://www.saucedemo.com"


def test_login_exitoso_con_credenciales_validas(page: Page):
    page.goto(BASE_URL)
    page.fill("#user-name", "standard_user")
    page.fill("#password", "secret_sauce")
    page.click("#login-button")

    expect(page).to_have_url(re.compile(r".*inventory\.html"))
    expect(page.locator(".title")).to_have_text("Products")


def test_login_fallido_con_credenciales_invalidas(page: Page):
    page.goto(BASE_URL)
    page.fill("#user-name", "usuario_invalido")
    page.fill("#password", "password_incorrecta")
    page.click("#login-button")

    expect(page).to_have_url(BASE_URL + "/")
    expect(page.locator('[data-test="error"]')).to_contain_text(
        "Username and password do not match"
    )


def test_login_fallido_con_usuario_bloqueado(page: Page):
    page.goto(BASE_URL)
    page.fill("#user-name", "locked_out_user")
    page.fill("#password", "secret_sauce")
    page.click("#login-button")

    expect(page.locator('[data-test="error"]')).to_contain_text(
        "Epic sadface: Sorry, this user has been locked out"
    )