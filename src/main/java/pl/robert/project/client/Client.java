package pl.robert.project.client;

import java.util.List;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import pl.robert.project.server.product.domain.ProductFacade;
import pl.robert.project.server.product.domain.dto.ProductDto;
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

        HorizontalLayout layout = new HorizontalLayout();
        VerticalLayout insertLayout = new VerticalLayout();
        VerticalLayout updateLayout = new VerticalLayout();
        VerticalLayout deleteLayout = new VerticalLayout();

        Label createLabel = new Label("Insert product");
        TextField createField = new TextField("Id");
        Button createBtn = new Button("Add");

        insertLayout.add(
                createLabel,
                createField,
                createBtn
        );

        Label updateLabel = new Label("Update product");
        TextField idField = new TextField("Id");
        TextField newNameField = new TextField("New name");
        Button updateBtn = new Button("Update");

        updateLayout.add(
                updateLabel,
                idField,
                newNameField,
                updateBtn
        );

        Label deleteLabel = new Label("Delete product");
        TextField deleteField = new TextField("Id");
        Button deleteBtn = new Button("Delete");

        deleteLayout.add(
                deleteLabel,
                deleteField,
                deleteBtn
        );

        createBtn.addClickListener(e -> {
            facade.create(new CreateProductDto(createField.getValue()));
            products.add(new ProductDto((long) products.size() + 1, createField.getValue()));

            createField.setValue("");

            grid.getDataProvider().refreshAll();
        });

        updateBtn.addClickListener(e -> {
            facade.update(Long.parseLong(idField.getValue()), newNameField.getValue());
            products.set((Integer.parseInt(idField.getValue()) - 1),
                    new ProductDto(Long.parseLong(idField.getValue()), newNameField.getValue()));

            idField.setValue("");
            newNameField.setValue("");

            grid.getDataProvider().refreshAll();
        });

        deleteBtn.addClickListener(e -> {
            products.remove(facade.read(Long.parseLong(deleteField.getValue())));

            facade.delete(Long.parseLong(deleteField.getValue()));

            deleteField.setValue("");

            grid.getDataProvider().refreshAll();
        });

        layout.add(
            insertLayout,
            updateLayout,
            deleteLayout
        );

        add(
            grid,
            layout
        );
    }
}
