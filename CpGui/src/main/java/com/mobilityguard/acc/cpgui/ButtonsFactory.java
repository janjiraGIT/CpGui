package com.mobilityguard.acc.cpgui;

import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class ButtonsFactory {
    //final Window window = new Window();

    /**
     * create button for save and cancel.
     */
    public void createSaveCancelButtons(final GridLayout grid, final String okSave,
                                    final String cancel, final int row, final int col) {
        final HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setStyleName("buttonBackground");
        final Button save = new Button(okSave);
        save.setStyleName("saveButton");
        final Button cancelButton = new Button(cancel);
        cancelButton.setStyleName("cancelButton");
        buttonLayout.addComponents(save,cancelButton);
        buttonLayout.setSpacing(true);
        grid.addComponent(buttonLayout,row,col);
    }

    /**
     * create change button.
     */
    public void createChangeButton(final GridLayout grid) {
        final VerticalLayout buttonLayout = new VerticalLayout();
        final Button change = new Button("Change");
        change.setStyleName("changeButton");
        buttonLayout.addComponents(change);
        grid.addComponent(buttonLayout,1,15);
        change.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                final UserChangWindow ucw = new UserChangWindow();
                ucw.changeUser(grid);
                String ok = "ok";
                String cancel = "cancel";
                final int col = 3;
                final int row = 20;
                createSaveCancelButtons(grid,ok,cancel,col,row );
            }
        });
    }
}
