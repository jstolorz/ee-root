package org.bluesoft.bean;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.View;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIOutput;
import jakarta.faces.component.html.*;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.facelets.Facelet;

import java.io.IOException;
import java.util.List;

import static jakarta.faces.application.StateManager.IS_BUILDING_INITIAL_STATE;

@View("/demo.xhtml")
@ApplicationScoped
public class DemoFacelet extends Facelet {
    @Override
    public void apply(final FacesContext facesContext, final UIComponent uiComponent) throws IOException {

        if (!facesContext.getAttributes().containsKey(IS_BUILDING_INITIAL_STATE)) {
            return;
        }

        ComponentBuilder components = new ComponentBuilder(facesContext);
        List<UIComponent> rootChildren = uiComponent.getChildren();

        UIOutput html = new UIOutput();

        html.setValue("<html>");
        rootChildren.add(html);

        HtmlBody body = components.create(HtmlBody.COMPONENT_TYPE);
        rootChildren.add(body);

        UIOutput h2 = new UIOutput();
        h2.setValue("<h2>Facelets Demo</h2>");

        body.getChildren().add(h2);

        HtmlForm form = components.create(HtmlForm.COMPONENT_TYPE);
        form.setId("form");
        body.getChildren().add(form);

        HtmlOutputLabel label = components.create(HtmlOutputLabel.COMPONENT_TYPE);
        label.setValue("Enter your name");
        form.getChildren().add(label);

        HtmlInputText message = components.create(HtmlInputText.COMPONENT_TYPE);
        message.setId("message");
        form.getChildren().add(message);

        HtmlOutputText text = components.create(HtmlOutputText.COMPONENT_TYPE);
        form.getChildren().add(text);

        HtmlCommandButton actionButton = components.create(HtmlCommandButton.COMPONENT_TYPE);
        actionButton.setId("button");
        actionButton.addActionListener(e -> text.setValue("Hi " + message.getValue()));
        actionButton.setValue("Do action");
        form.getChildren().add(actionButton);


        UIOutput htmlEnd = new UIOutput();
        htmlEnd.setValue("</html>");
        rootChildren.add(htmlEnd);

    }

    private static class ComponentBuilder {
        private FacesContext facesContext;

        ComponentBuilder(final FacesContext facesContext) {
            this.facesContext = facesContext;
        }

        <T> T create(String componentType){
            return (T) facesContext.getApplication().createComponent(facesContext, componentType,null);
        }
    }

}
