package ru.tarabne.pages.components;

import static com.codeborne.selenide.Selenide.*;

public class NotificationWidgetComponent {
    public void closeNotificationWidget() {
        sleep(1000);
        executeJavaScript("$('.flocktory-widget-overlay').remove()");
    }
}
