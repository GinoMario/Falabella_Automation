package steps;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.testng.asserts.SoftAssert;
import pages.HomePage;

import java.util.Arrays;
import java.util.List;


public class IniciarSesionSteps extends TestBase {

    /**
     * Metodo que verifica que cargó correctamente el home page
     * @throws Exception
     */
    @Given("^El usuario se encuentra en el home de falabella$")
    public void elUsuarioSeEncuentraEnElHomeDeFalabella() throws Exception {
        Assert.assertTrue("Error: No cargó correctamente el Home Page",homePage.isHomePageLoad());
    }

    /**
     * Metodo que selecciona la opción "iniciar sesion" para abrir el modal
     * @throws Exception
     */
    @When("^Selecciono la opción iniciar sesión$")
    public void seleccionoLaOpciónIniciarSesión() throws Exception {
        homePage.clicOnLogin();
        Assert.assertTrue("Error: No cargó correctamente el modal",homePage.isLoginModalLoad());
    }

    /**
     * Metodo que verifica todos los elementos que cargan en el modal
     * @throws Exception
     */
    @Then("^Verifico todos los elementos existentes$")
    public void verificoTodosLosElementosExistentes() throws Exception {
        homePage.validateElements();
    }

    /**
     * Metodo que verifica los mensajes de validación de los campos del modal
     * @param table
     * @throws Exception
     */
    @Then("^Verifico los mensajes de error$")
    public void verificoLosMensajesDeError(DataTable table) throws Exception {
        SoftAssert sa = new SoftAssert();
        List < List <String> > rows = table.asLists(String.class);
        List < List <String> > rowsClean = rows.subList(1, rows.size());

        for (List <String> row : rowsClean){
            String aMail = row.get(0);
            String aPassword = row.get(1);
            String aErrorMessageExpect = row.get(2);

            homePage.setMail(aMail);
            homePage.setPassword(aPassword);
            if (aErrorMessageExpect.length() > 0) {
                String[] listErrorMessage = aErrorMessageExpect.split(",");
                sa.assertEquals(homePage.getListErrorMessages(), Arrays.asList(listErrorMessage),"Error: El mensaje de error es inválido");
            }
        }
        sa.assertAll();
    }

    /**
     * Metodo que verifica el estado del boton "Ingresar"
     * @param table
     * @throws Exception
     */
    @Then("^Verifico el estado del boton$")
    public void verificoElEstadoDelBoton(DataTable table) throws Exception {
        SoftAssert sa = new SoftAssert();
        List < List <String> > rows = table.asLists(String.class);
        List < List <String> > rowsClean = rows.subList(1, rows.size());

        for (List <String> row : rowsClean){
            String aMail = row.get(0);
            String aPassword = row.get(1);
            boolean aStatusButton = Boolean.parseBoolean(row.get(2));

            homePage.setMail(aMail);
            homePage.setPassword(aPassword);
            sa.assertEquals(aStatusButton,homePage.getStatusButton(),"Error: El estado del botón es incorrecto");
        }
        sa.assertAll();
    }

    /**
     * Metodo que permite el seteo de usuario y contraseña para luego dar clic en ingresar
     * @param mail
     * @param pass
     * @throws Throwable
     */
    @And("^Ingreso los datos usuario \"([^\"]*)\" y contraseña \"([^\"]*)\"$")
    public void ingresoLosDatosUsuarioYContraseña(String mail, String pass) throws Throwable {
        homePage.setMail(mail);
        homePage.setPassword(pass);
        homePage.clicOnBtnInto();
    }

    /**
     * Metodo que permite verificar el resultado del logeo
     * @param result
     * @throws Throwable
     */
    @Then("^Verifico el resultado esperado \"([^\"]*)\"$")
    public void verificoElResultadoEsperado(String result) throws Throwable {
        Assert.assertEquals("Error: El resultado esperado es incorrecto",Boolean.parseBoolean(result),homePage.getResultLogin());
    }

    /**
     * Metodo que cierra el modal
     * @throws Exception
     */
    @And("^Cierro el modal$")
    public void cierroElModal() throws Exception {
        homePage.clicOnClose();
    }

    /**
     * Metodo que verifica la carga del modal
     * @throws Exception
     */
    @Then("^Verifico el cierre del modal$")
    public void verificoElCierreDelModal() throws Exception {
        Assert.assertFalse("Error: No se cerró correctamente el modal",homePage.isBtnDisplayed());
    }
}
