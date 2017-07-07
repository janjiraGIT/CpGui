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
    public void changeUser(final GridLayout grid) {
        //final Window userWindow = new Window("Change admin credentials");
        final VerticalLayout layoutUser = new VerticalLayout();
        layoutUser.setSizeFull();
        final Label adUser = new Label(ADMIN_USER);
        adUser.setStyleName("adUser");
        final Label adPass = new Label(ADMIN_PASSWORD);
        adPass.setStyleName("adPass");
        final Label adPassAgain = new Label(ADMIN_PASSWORD_AGAIN);
        adPassAgain.setStyleName("adPassAgain");
        final TextField tfUser = new TextField();
        final TextField tfPass = new TextField();
        final TextField tfPassAgain = new TextField();

        grid.addComponent(adUser,1,15);
        grid.addComponent(adPass,1,16);
        grid.addComponent(adPassAgain,1,17);
        grid.addComponent(tfUser,2,15);
        grid.addComponent(tfPass,2,16);
        grid.addComponent(tfPassAgain,2,17);
    }
}
