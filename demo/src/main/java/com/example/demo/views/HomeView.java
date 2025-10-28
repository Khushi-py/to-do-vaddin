package com.example.demo.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class HomeView extends VerticalLayout {

    public HomeView() {
        VerticalLayout todosList = new VerticalLayout();
        TextField taskField = new TextField();
        Button addButton = new Button("Add");

        addButton.addClickListener(click -> {
            if (!taskField.getValue().trim().isEmpty()) {
                Checkbox checkbox = new Checkbox(taskField.getValue());
                Button editButton = new Button("Edit");
                Button deleteButton = new Button("Delete");

                HorizontalLayout taskLayout = new HorizontalLayout(checkbox, editButton, deleteButton);

                deleteButton.addClickListener(e -> todosList.remove(taskLayout));

                editButton.addClickListener(e -> {
                    TextField editField = new TextField();
                    editField.setValue(checkbox.getLabel());
                    Button saveButton = new Button("Save");
                    Button cancelButton = new Button("Cancel");

                    HorizontalLayout editLayout = new HorizontalLayout(editField, saveButton, cancelButton);
                    todosList.replace(taskLayout, editLayout);

                    saveButton.addClickListener(ev -> {
                        checkbox.setLabel(editField.getValue());
                        todosList.replace(editLayout, taskLayout);
                    });

                    cancelButton.addClickListener(ev -> {
                        todosList.replace(editLayout, taskLayout);
                    });
                });

                todosList.add(taskLayout);
                taskField.clear();
            }
        });

        addButton.addClickShortcut(Key.ENTER);

        add(
                new H1("My Todo"),
                todosList,
                new HorizontalLayout(taskField, addButton)
        );
    }
}