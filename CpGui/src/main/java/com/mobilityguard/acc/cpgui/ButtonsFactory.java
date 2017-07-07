package com.mobilityguard.acc.cpgui;

import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class ButtonsFactory {
    final Window window = new Window();

    /**
     * create button for save and cancel.
     */
    public void createSaveCancelButtons(final GridLayout grid, final String text1, 
    									final String text2, final int row, final int col) {
        final HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setStyleName("buttonBackground");
        final Button save = new Button(text1);
        save.setStyleName("saveButton");
        final Button cancel = new Button(text2);
        cancel.setStyleName("cancelButton");
        buttonLayout.addComponents(save,cancel);
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
        grid.addComponent(buttonLayout,2,14);
        change.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                final UserChangWindow ucw = new UserChangWindow();
                ucw.changeUser(grid);
                String text1 = "ok";
                String text2 = "cancel";
                final int col = 3;
                final int row = 20;
                createSaveCancelButtons(grid,text1,text2,col,row );
            }
        });
    }
}
