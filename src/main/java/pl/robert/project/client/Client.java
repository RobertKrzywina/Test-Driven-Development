package pl.robert.project.client;

import java.util.List;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import pl.robert.project.server.product.domain.ProductFacade;
import pl.robert.project.server.product.domain.dto.ProductDto;
import pl.robert.project.server.shared.ParameterizedException;
import pl.robert.project.server.product.domain.dto.CreateProductDto;

@Route("")
@Theme(Lumo.class)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Client extends VerticalLayout {

    ProductFacade facade;

    public Client(ProductFacade facade) {
        this.facade = facade;

        addHeader();
        addContent();
    }

    private void addHeader() {
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(new Label("Product management"));
    }

    private void addContent() {
        List<ProductDto> products = facade.readAll();

        Grid<ProductDto> grid = new Grid<>();

        grid.setItems(products);

        grid.addColumn(ProductDto::getId).setHeader("Id");
        grid.addColumn(ProductDto::getName).setHeader("Name");

        TextField nameField = new TextField("Name");
        Button createBtn = new Button("Add");

        createBtn.addClickListener(e -> {
            facade.create(new CreateProductDto(nameField.getValue()));

            products.add(new ProductDto((long) products.size() + 1, nameField.getValue()));

            nameField.setValue("");

            grid.getDataProvider().refreshAll();
        });

        TextField updateIdField = new TextField("Id");
        TextField newNameField = new TextField("New name");
        Button updateBtn = new Button("Update");

        updateBtn.addClickListener(e -> {
            facade.update(Long.parseLong(updateIdField.getValue()), newNameField.getValue());

            products.set((Integer.parseInt(updateIdField.getValue()) - 1),
                    new ProductDto(Long.parseLong(updateIdField.getValue()), newNameField.getValue()));

            updateIdField.setValue("");
            newNameField.setValue("");

            grid.getDataProvider().refreshAll();
        });

        TextField deleteIdField = new TextField("Id");
        Button deleteBtn = new Button("Delete");

        deleteBtn.addClickListener(e -> {
            products.remove(facade.read(Long.parseLong(deleteIdField.getValue())));

            facade.delete(Long.parseLong(deleteIdField.getValue()));

            deleteIdField.setValue("");

            grid.getDataProvider().refreshAll();
        });

        VaadinSession.getCurrent().setErrorHandler((handler) ->
                Notification.show(ParameterizedException.label, 3000, Notification.Position.MIDDLE)
        );

        HorizontalLayout layout = new HorizontalLayout(
            new VerticalLayout(new Label("Create"), nameField, createBtn),
            new VerticalLayout(new Label("Update"), updateIdField, newNameField, updateBtn),
            new VerticalLayout(new Label("Delete"), deleteIdField, deleteBtn)
        );

        add(grid, layout);
    }
}
