package com.equitativa.temp;

import com.equitativa.TaskService;
import com.equitativa.base.BasePage;
import com.equitativa.model.Priority;
import com.equitativa.model.Task;
import com.equitativa.panel.TaskPanel;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.stream.Collectors.groupingBy;

public class Temp  extends BasePage {






    public Temp() {

    }

//    private void addTaskLabel(Priority priority) {
//        var activityLabel = new Label("label",priority.toString());
//        AttributeAppender attributeAppender = switch (priority) {
//            case LOW -> new AttributeAppender("style", "background-color: blue !important;");
//            case MEDIUM -> new AttributeAppender("style", "background-color: #ffc107 !important;");
//            case HIGH -> new AttributeAppender("style", "background-color: red !important;");
//        };
//        activityLabel.add(attributeAppender);
//        add(activityLabel);
//    }
}