package com.mobilityguard.acc.cpgui;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class UserChangWindow {
    private static final String ADMIN_PASSWORD_AGAIN = "Admin password again:";
    private static final String ADMIN_PASSWORD = "Admin password : ";
    private static final String ADMIN_USER = "Admin User : ";

    /**
     * @return ChangUser window.
     */
    public void changeUser() {
        final Window userWindow = new Window("Change admin credentials");
        final VerticalLayout layoutUser = new VerticalLayout();
        layoutUser.setSizeFull();
        userWindow.setContent(layoutUser);
        userWindow.center();
        userWindow.setHeight("80%");
        userWindow.setWidth("50%");

        final GridLayout gdUser = new GridLayout(4,10);
        gdUser.setWidth("1000px");
        gdUser.setHeight("700px");

        final Label adUser = new Label(ADMIN_USER);
        adUser.setStyleName("adUser");
        final Label adPass = new Label(ADMIN_PASSWORD);
        adPass.setStyleName("adPass");
        final Label adPassAgain = new Label(ADMIN_PASSWORD_AGAIN);
        adPassAgain.setStyleName("adPassAgain");
        final TextField tfUser = new TextField();
        final TextField tfPass = new TextField();
        final TextField tfPassAgain = new TextField();

        gdUser.addComponent(adUser,1,1);
        gdUser.addComponent(adPass,1,2);
        gdUser.addComponent(adPassAgain,1,3);
        gdUser.addComponent(tfUser,2,1);
        gdUser.addComponent(tfPass,2,2);
        gdUser.addComponent(tfPassAgain,2,3);

       // return userWindow;

    }
}
