package ru.tarabne.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.tarabne.pages.RegistrationPage;
import ru.tarabne.testdata.RegistrationTestData;

@Story("Регистрация и авторизация пользователя")
@Feature("UI | Регистрация пользователя")
@Issue("MXD-102")
@DisplayName("Тесты на регистрацию пользователя")
public class RegistrationTests extends BaseTest {
    RegistrationPage registrationPage = new RegistrationPage();
    RegistrationTestData registrationTestData = new RegistrationTestData();

    @Test
    @DisplayName("Проверка получения сообщения об отправке СМС с кодом при успешной регистрации")
    @Owner("tarabne")
    @Severity(SeverityLevel.CRITICAL)
    void shouldReceiveAMessageAboutSendingSmsWithCodeTest() {
        registrationPage.openRegistrationPage()
                .setFirstName(registrationTestData.firstName)
                .setLastName(registrationTestData.lastName)
                .setEmail(registrationTestData.userEmail)
                .setPhone(registrationTestData.userPhone)
                .setPassword(registrationTestData.userPassword)
                .setRuleAcceptanceCheckbox()
                .passReCaptcha()
                .pressSubmitButton();

        registrationPage.registrationSuccessCheck();
    }

    @Test
    @DisplayName("Проверка получения ошибки ввода капчи")
    @Owner("tarabne")
    @Severity(SeverityLevel.NORMAL)
    void shouldReceiveCaptchaErrorMessageTest() {
        registrationPage.openRegistrationPage()
                .setFirstName(registrationTestData.firstName)
                .setLastName(registrationTestData.lastName)
                .setEmail(registrationTestData.userEmail)
                .setPhone(registrationTestData.userPhone)
                .setPassword(registrationTestData.userPassword)
                .setRuleAcceptanceCheckbox()
                .pressSubmitButton();

        registrationPage.registrationFailureCheck()
                .captchaErrorCheck();
    }

    @Test
    @DisplayName("Проверка получения ошибки об отсутствии согласия с правилами сайта")
    @Owner("tarabne")
    @Severity(SeverityLevel.NORMAL)
    void shouldReceiveRuleAcceptanceErrorMessageTest() {
        registrationPage.openRegistrationPage()
                .setFirstName(registrationTestData.firstName)
                .setLastName(registrationTestData.lastName)
                .setEmail(registrationTestData.userEmail)
                .setPhone(registrationTestData.userPhone)
                .setPassword(registrationTestData.userPassword)
                .pressSubmitButton();

        registrationPage.registrationFailureCheck()
                .ruleAcceptanceErrorCheck();
    }
}
