package seedu.address.logic.commands.assignment;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ASSIGNMENTS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "list-a";

    public static final String MESSAGE_SUCCESS = "Listed all assignments";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAssignmentList(PREDICATE_SHOW_ALL_ASSIGNMENTS);
        return new CommandResult(MESSAGE_SUCCESS);
}
}
