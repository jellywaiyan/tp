package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.internshiptask.InternshipTask;
import java.util.Comparator;

public class InternshipTaskCard extends UiPart<Region> {
    private final InternshipTask task;

    private static final String FXML = "InternshipTaskCard.fxml";

    @FXML
    private Label id;

    @FXML
    private Label roleDetails;

    @FXML
    private Label name;

    @FXML
    private Label deadline;

    @FXML
    private Label status;

    @FXML
    private Label outcome;

    @FXML
    private FlowPane tags;

    public InternshipTaskCard(InternshipTask task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ", ");
        roleDetails.setText(task.getInternshipRole().getMainDetails());
        name.setText(task.getTaskName().toString());
        deadline.setText(task.getDeadline().toString());
        status.setText(task.getStatus().toString());
        outcome.setText(task.getOutcome().toString());
        task.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
