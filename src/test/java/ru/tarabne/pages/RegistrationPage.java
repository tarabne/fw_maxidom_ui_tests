package ru.tarabne.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private SelenideElement firstNameInput = $("#register_phis_name"),
        lastNameInput = $("#register_phis_last_name"),
        emailInput = $("#register_phis_login"),
        phoneInput = $("#register_phis_phone"),
        passwordInput = $("#register_phis_password"),
        ruleAcceptanceInput = $("#register_phis_rules"),
        reCaptchaInput = $(".box-reg-user-container").$("iframe"),
        registrationSubmitButton = $("#reg_phis_submit"),
        registrationVerificationWindow = $("#person-code"),
        registrationVerificationText = registrationVerificationWindow.$(".verification"),
        ruleAcceptanceError = ruleAcceptanceInput.parent().$("span.error"),
        captchaError = $("#regErrorMessage");


    @Step("Перейти на страницу регистрации пользователя")
    public RegistrationPage openRegistrationPage() {
        open("");
        $(".header__account-links").click();
        $("#reg-person").click();
        return this;
    }
    @Step("Заполнить имя пользователя")
    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }
    @Step("Заполнить фамилию пользователя")
    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }
    @Step("Заполнить email пользователя")
    public RegistrationPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }
    @Step("Заполнить телефон пользователя")
    public RegistrationPage setPhone(String value) {
        phoneInput.setValue(value);
        return this;
    }
    @Step("Заполнить пароль пользователя")
    public RegistrationPage setPassword(String value) {
        passwordInput.setValue(value);
        return this;
    }

    @Step("Принять правила сайта, включив чекбокс")
    public RegistrationPage setRuleAcceptanceCheckbox() {
        ruleAcceptanceInput.click();
        return this;
    }

    @Step("Пройти капчу")
    public  RegistrationPage passReCaptcha() {
        reCaptchaInput.click();
        return this;
    }
    @Step("Нажать на кнопку \"Зарегистрироваться\"")
    public RegistrationPage pressSubmitButton() {
        registrationSubmitButton.click();
        return this;
    }

    @Step("Проверить наличие сообщения об отправке СМС с кодом")
    public RegistrationPage registrationSuccessCheck() {
        registrationVerificationWindow.shouldBe(visible);
        registrationVerificationText
                .shouldHave(text("На ваш телефон отправлено SMS с кодом подтверждения."));
        return this;
    }

    @Step("Проверить наличие ошибки об отсутствии согласия с правилами сайта")
    public RegistrationPage ruleAcceptanceErrorCheck() {
        ruleAcceptanceError.shouldBe(visible).shouldHave(text("Это поле необходимо заполнить."));
        return this;
    }

    @Step("Проверить наличие ошибки введения капчи")
    public RegistrationPage captchaErrorCheck() {
        captchaError.shouldBe(visible, Duration.ofSeconds(10)).shouldHave(text("Captcha введена не верно"));
        return this;
    }

    @Step("Проверить отсутствие сообщения об отправке СМС с кодом")
    public RegistrationPage registrationFailureCheck() {
        registrationVerificationWindow.shouldNotBe(visible);
        return this;
    }

}
