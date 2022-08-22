package pages;

import org.example.framework.base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;


public class HomePage extends BasePage {

    @FindBy(xpath = "//div[@class='Popover-module_popover-container__3qpkj Popover-module_bottom-caret-notlogin__19OPO']")
    private WebElement btnLogIn;

    @FindBy(xpath = "//a[text()=\"Inicia sesión\"]")
    private WebElement lblLogIn;

    /*************** MODAL ************/
    @FindBy(xpath = "//button[@id='testId-cc-modal-close-button']")
    private WebElement btnClose;

    @FindBy(how = How.CSS, using = "#testId-cc-login-form > div > div.login-form-module_header-logo__3XIy3 > svg")
    private WebElement iconFalabella;

    @FindBy(xpath = "//h3[text()=\"¡Qué bueno tenerte de vuelta!\"]")
    private WebElement lblTittleModal;

    @FindBy(xpath = "//label[text()=\"Correo electrónico\"]")
    private WebElement lblMail;

    @FindBy(xpath = "//input[@id='testId-cc-login-form-email-input']")
    private WebElement inputMail;

    @FindBy(xpath = "//label[text()=\"Contraseña\"]")
    private WebElement lblPassword;

    @FindBy(xpath = "//input[@id='testId-cc-login-form-password-input']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[@id='testId-cc-login-form-password-icon-button']")
    private WebElement btnHideEye;

    @FindBy(xpath = "//a[text()=\"Restablecer contraseña\"]")
    private WebElement linkRestorePass;

    @FindBys({@FindBy(how = How.XPATH, using = "//div[@class='form-field-module_field-error__33p44']")})
    private List<WebElement> listLoginErrors;

    @FindBy(xpath = "//button[@id='testId-cc-login-form-submit']")
    private WebElement btnGetInto;

    @FindBy(xpath = "//p[text()=\"¿Aún no tienes cuenta?\"]")
    private WebElement lblNotAccount;

    @FindBy(xpath = "//a[text()=\"Regístrate\"]")
    private WebElement linkRegister;

    @FindBy(xpath = "//div[text()=\"Correo electrónico o contraseña incorrecta. Por favor, vuelve a intentarlo nuevamente.\"]")
    private WebElement lblResult;

    /**********************************/

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isHomePageLoad() throws Exception {
        return this.isDisplayed(btnLogIn);
    }

    public boolean isLoginModalLoad() throws Exception {
        sleep(500);
        return this.isDisplayed(btnGetInto);
    }

    public void clicOnLogin() throws Exception {
        btnLogIn.click();
        lblLogIn.click();
    }

    public List<String> getListErrorMessages() {
        ArrayList<String> listErrorMessages = new ArrayList<String>();
        for (WebElement e : listLoginErrors) {
            if (e.getText().length() > 0) {
                listErrorMessages.add(e.getText().toString());
            }
        }
        return listErrorMessages;
    }


    public void validateElements() throws Exception {
        SoftAssert sa = new SoftAssert();

        sa.assertTrue(this.isDisplayed(btnClose), "Element (btnClose) isn't displayed");
        sa.assertTrue(this.isDisplayed(iconFalabella), "Element (iconFalabella) isn't displayed");
        sa.assertTrue(this.isDisplayed(lblTittleModal), "Element (lblTittleModal) isn't displayed");
        sa.assertTrue(this.isDisplayed(lblMail), "Element (lblMail) isn't displayed");
        sa.assertTrue(this.isDisplayed(inputMail), "Element (inputMail) isn't displayed");
        sa.assertTrue(this.isDisplayed(lblPassword), "Element (lblPassword) isn't displayed");
        sa.assertTrue(this.isDisplayed(inputPassword), "Element (inputPassword) isn't displayed");
        sa.assertTrue(this.isDisplayed(btnHideEye), "Element (btnHideEye) isn't displayed");
        sa.assertTrue(this.isDisplayed(linkRestorePass), "Element (linkRestorePass) isn't displayed");
        sa.assertTrue(this.isDisplayed(btnGetInto), "Element (btnGetInto) isn't displayed");
        sa.assertTrue(this.isDisplayed(lblNotAccount), "Element (lblNotAccount) isn't displayed");
        sa.assertTrue(this.isDisplayed(linkRegister), "Element (linkRegister) isn't displayed");
        sa.assertAll();
    }

    public void setMail(String sMail) throws Exception {
        inputMail.sendKeys(sMail);
        inputMail.sendKeys(Keys.TAB);
    }

    public void setPassword(String sPassword) throws Exception {
        inputPassword.clear();
        inputPassword.sendKeys(sPassword);
        inputPassword.sendKeys(Keys.TAB);
    }

    public boolean getStatusButton() throws Exception {
        return btnGetInto.isEnabled();
    }

    public void clicOnBtnInto() throws Exception {
        btnGetInto.click();
    }

    public boolean getResultLogin() throws Exception {
        return this.isDisplayed(lblResult);
    }

    public void clicOnClose() throws Exception {
        btnClose.click();
        sleep(500);
    }

    public boolean isBtnDisplayed(){
        try {
            return btnGetInto.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
