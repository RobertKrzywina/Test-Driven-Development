package pl.robert.project.client;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import org.springframework.data.domain.PageRequest;

import pl.robert.project.server.product.domain.ProductFacade;
import pl.robert.project.server.product.domain.dto.ProductDto;

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
        List<ProductDto> products = facade.readAll(PageRequest.of(0, 10))
                .get()
                .collect(Collectors.toList());

        Grid<ProductDto> grid = new Grid<>();

        grid.setItems(products);

        grid.addColumn(ProductDto::getId).setHeader("Id");
        grid.addColumn(ProductDto::getName).setHeader("Name");

        add(grid);
    }
}
