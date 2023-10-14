package com.urise.webapp.model;

public enum ContactType {

    MOBILE("Мобильный"),
    MAIL("Почта"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    TELEGRAM("Телеграм");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}