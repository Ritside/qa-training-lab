import pytest
from selenium import webdriver
from selenium.webdriver.chrome.options import Options


@pytest.fixture
def driver():
    options = Options()
    # Deshabilitar detección de contraseñas filtradas y gestor de contraseñas
    # (mismo problema que encontramos en Java: Chrome real interfiere con
    # ejecuciones automatizadas que reusan credenciales repetidamente)
    prefs = {
        "profile.password_manager_leak_detection": False,
        "credentials_enable_service": False,
        "profile.password_manager_enabled": False,
    }
    options.add_experimental_option("prefs", prefs)

    drv = webdriver.Chrome(options=options)
    drv.implicitly_wait(5)

    yield drv

    drv.quit()