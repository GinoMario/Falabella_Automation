Feature: Iniciar sesión

  COMO usuario QUIERO ingresar mis credenciales PARA loguearme a la web page de falabella

  Background:
    Given El usuario se encuentra en el home de falabella
    When Selecciono la opción iniciar sesión

  @smokeTest @regression
  Scenario: Verificar los elementos del modal iniciar sesión
    Then Verifico todos los elementos existentes

  @regression
  Scenario: Verificar los mensajes de error del modal iniciar sesión
    Then Verifico los mensajes de error
      | correo       | contraseña | mensaje de error                                                                 |
      |              |            | Ingresa un correo electrónico,Ingresa una contraseña                             |
      | ginomario    |            | Ingresa un correo electrónico válido (nombre@ejemplo.com),Ingresa una contraseña |
      | jt@gmail.com |            | Ingresa una contraseña                                                           |

  @regression
  Scenario: Verificar el estado del boton ingresar
    Then Verifico el estado del boton
      | correo       | contraseña | estado del boton |
      |              |            | false            |
      | ginomario    |            | false            |
      | jt@gmail.com | 12345678   | true             |

  @regression
  Scenario Outline: Verificar el inicio de sesión
    And Ingreso los datos usuario "<correo>" y contraseña "<contraseña>"
    Then Verifico el resultado esperado "<is failed>"
    Examples:
      | correo                | contraseña | is failed |
      | ginomariojt@gmail.com | 12345678   | true      |

  @regression
  Scenario: Verificar el cierre del modal
    And Cierro el modal
    Then Verifico el cierre del modal
