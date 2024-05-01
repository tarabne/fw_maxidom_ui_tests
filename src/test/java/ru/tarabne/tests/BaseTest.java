package ru.tarabne.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.tarabne.config.DriverConfig;
import ru.tarabne.helpers.Attach;

import java.util.Map;

public class BaseTest {

    @BeforeAll
    static void beforeAll() {
        DriverConfig driverConfig = ConfigFactory.create(DriverConfig.class);

        Configuration.browser = driverConfig.browserName();
        Configuration.browserSize = driverConfig.browserSize();
        Configuration.browserVersion = driverConfig.browserVersion();
        Configuration.remote = driverConfig.remoteUrl();
        Configuration.baseUrl = "https://www.maxidom.ru";
        Configuration.pageLoadStrategy = "eager";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
}
