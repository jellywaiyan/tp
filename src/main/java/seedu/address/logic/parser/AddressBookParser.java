package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.assignment.AddAssignmentCommand;
import seedu.address.logic.commands.assignment.DeleteAssignmentCommand;
import seedu.address.logic.commands.assignment.EditAssignmentCommand;
import seedu.address.logic.commands.assignment.FindAssignmentCommand;
import seedu.address.logic.commands.assignment.ListAssignmentCommand;
import seedu.address.logic.commands.assignment.MarkAssignmentCommand;
import seedu.address.logic.commands.assignment.SortAssignmentCommand;
import seedu.address.logic.commands.assignment.UnMarkAssignmentCommand;
import seedu.address.logic.commands.person.AddCommand;
import seedu.address.logic.commands.person.DeleteCommand;
import seedu.address.logic.commands.person.EditCommand;
import seedu.address.logic.commands.person.FindCommand;
import seedu.address.logic.commands.person.ListCommand;
import seedu.address.logic.parser.assignment.AddAssignmentParser;
import seedu.address.logic.parser.assignment.DeleteAssignmentParser;
import seedu.address.logic.parser.assignment.EditAssignmentParser;
import seedu.address.logic.parser.assignment.FindAssignmentCommandParser;
import seedu.address.logic.parser.assignment.ListAssignmentParser;
import seedu.address.logic.parser.assignment.MarkAssignmentParser;
import seedu.address.logic.parser.assignment.UnMarkAssignmentParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.person.AddCommandParser;
import seedu.address.logic.parser.person.DeleteCommandParser;
import seedu.address.logic.parser.person.EditCommandParser;
import seedu.address.logic.parser.person.FindCommandParser;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddAssignmentCommand.COMMAND_WORD:
            return new AddAssignmentParser().parse(arguments);

        case MarkAssignmentCommand.COMMAND_WORD:
            return new MarkAssignmentParser().parse(arguments);

        case UnMarkAssignmentCommand.COMMAND_WORD:
            return new UnMarkAssignmentParser().parse(arguments);

        case ListAssignmentCommand.COMMAND_WORD:
            return new ListAssignmentParser().parse(arguments);

        case DeleteAssignmentCommand.COMMAND_WORD:
            return new DeleteAssignmentParser().parse(arguments);

        case FindAssignmentCommand.COMMAND_WORD:
            return new FindAssignmentCommandParser().parse(arguments);

        case EditAssignmentCommand.COMMAND_WORD:
            return new EditAssignmentParser().parse(arguments);

        case SortAssignmentCommand.COMMAND_WORD:
            return new SortAssignmentCommand();

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
