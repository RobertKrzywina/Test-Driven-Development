package pl.robert.project.client;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "")
public class Client extends VerticalLayout {

    public Client() {
        VerticalLayout layout = new VerticalLayout();

        layout.add(new Label("Hello world"));

        add(layout);
    }
}
