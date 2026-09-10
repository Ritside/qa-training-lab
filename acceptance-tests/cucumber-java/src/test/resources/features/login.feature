Feature: Login
  Como usuario de SauceDemo
  Quiero poder iniciar sesión
  Para acceder al catálogo de productos

  Scenario: Login exitoso con credenciales válidas
    Given el usuario está en la página de login
    When ingresa el usuario "standard_user" y la contraseña "secret_sauce"
    Then debería ver la página de productos

  Scenario: Login fallido con credenciales inválidas
    Given el usuario está en la página de login
    When ingresa el usuario "usuario_invalido" y la contraseña "password_incorrecta"
    Then debería ver el mensaje de error "Username and password do not match"

  Scenario: Login fallido con usuario bloqueado
    Given el usuario está en la página de login
    When ingresa el usuario "locked_out_user" y la contraseña "secret_sauce"
    Then debería ver el mensaje de error "Epic sadface: Sorry, this user has been locked out"