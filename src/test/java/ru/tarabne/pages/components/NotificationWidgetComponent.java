package ru.tarabne.pages.components;

import static com.codeborne.selenide.Selenide.*;

public class NotificationWidgetComponent {
    public void closeNotificationWidget() {
        sleep(1000);
        executeJavaScript("$('#fl-394060').remove()");
    }
}
