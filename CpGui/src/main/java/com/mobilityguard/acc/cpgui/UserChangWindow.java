package com.mobilityguard.acc.cpgui;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class UserChangWindow {
    private static final String ADMIN_PASSWORD_AGAIN = "Admin password again:";
    private static final String ADMIN_PASSWORD = "Admin password : ";
    private static final String ADMIN_USER = "Admin User : ";

     /**
     * @param grid.
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

        grid.addComponent(adUser,0,16);
        grid.addComponent(adPass,0,17);
        grid.addComponent(adPassAgain,0,18);
        grid.addComponent(tfUser,1,16);
        grid.addComponent(tfPass,1,17);
        grid.addComponent(tfPassAgain,1,18);
    }
}