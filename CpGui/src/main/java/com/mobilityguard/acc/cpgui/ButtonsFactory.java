package com.mobilityguard.acc.cpgui;

import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class ButtonsFactory {
    Window window = new Window();

    /**
     * create button for save and cancel.
     */
    public void createSaveCancelButtons(final GridLayout grid) {
        final HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setStyleName("buttonBackground");
        final Button save = new Button("save");
        save.setStyleName("saveButton");
        final Button cancel = new Button("cancel");
        cancel.setStyleName("cancelButton");
        buttonLayout.addComponents(save,cancel);
        grid.addComponent(buttonLayout,4,22);
    }

    /**
     * create button for save and cancel.
     */
    public void createChangeButtons(final GridLayout grid) {
        final HorizontalLayout buttonLayout = new HorizontalLayout();
        final Button change = new Button("Change");
        final Window ucW = new Window();
        change.setStyleName("changeButton");
        buttonLayout.addComponents(change);
        grid.addComponent(buttonLayout,2,14);
        change.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                final UserChangWindow ucw = new UserChangWindow();
                // window = ucw.changeUser();
                Notification.show("test");
            }
        });
    }
}
