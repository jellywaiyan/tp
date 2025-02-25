package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.assignment.exceptions.AssignmentNotFoundException;
import seedu.address.model.assignment.exceptions.DuplicateAssignmentException;

/**
 * A list of assignments that enforces uniqueness between its elements and does not allow nulls.
 */
public class UniqueAssignmentList implements Iterable<Assignment> {

    private final ObservableList<Assignment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Assignment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent assignment as the given argument.
     */
    public boolean contains(Assignment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAssignment);
    }

    /**
     * Adds an assignment to the list.
     * The assignment must not already exist in the list.
     */
    public void add(Assignment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAssignmentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the assignment {@code target} in the list with {@code editedAssignment}.
     * {@code target} must exist in the list.
     * The assignment identity of {@code editedPerson} must not be
     * the same as another existing assignment in the list.
     */
    public void setAssignment(Assignment target, Assignment editedAssignment) {
        requireAllNonNull(target, editedAssignment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AssignmentNotFoundException();
        }

        if (!target.isSameAssignment(editedAssignment) && contains(editedAssignment)) {
            throw new DuplicateAssignmentException();
        }

        internalList.set(index, editedAssignment);
    }

    /**
     * Removes the assignment person from the list.
     * The assignment must exist in the list.
     */
    public void remove(Assignment toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AssignmentNotFoundException();
        }
    }

    /**
     * Mark an assignment as complete.
     * The assignment must exist in the list.
     */
    public void mark(Assignment toMark) {
        requireNonNull(toMark);
        int index = internalList.indexOf(toMark);
        if (index == -1) {
            throw new AssignmentNotFoundException();
        }
        internalList.get(index).mark();
        internalList.set(index, internalList.get(index));
    }

    /**
     * UnMarks an assignment and sets its status as incomplete.
     * The assignment must exist in the list.
     */
    public void unMark(Assignment toUnMark) {
        requireNonNull(toUnMark);
        int index = internalList.indexOf(toUnMark);
        if (index == -1) {
            throw new AssignmentNotFoundException();
        }
        internalList.get(index).unMark();
        internalList.set(index, internalList.get(index));
    }

    /**
     * Replaces the description of {@code assignment} in the list with {@code newDescription}.
     * {@code assignment} must exist in the list.
     *
     * @param assignment     The assignment to be edited
     * @param newDescription The description to replace the current assignment's description
     */
    public void edit(Assignment assignment, Description newDescription) {
        requireNonNull(assignment);
        int index = internalList.indexOf(assignment);
        if (index < 0) {
            throw new AssignmentNotFoundException();
        }
        Assignment assignmentToEdit = internalList.get(index);
        assignmentToEdit.setDescription(newDescription);
        internalList.set(index, assignmentToEdit);
    }

    public void setAssignments(UniqueAssignmentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    public void setAssignments(List<Assignment> assignments) {
        requireAllNonNull(assignments);
        if (!assignmentsAreUnique(assignments)) {
            throw new DuplicateAssignmentException();
        }

        internalList.setAll(assignments);
    }

    public ObservableList<Assignment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Assignment> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueAssignmentList)) {
            return false;
        }

        UniqueAssignmentList otherUniqueAssignmentList = (UniqueAssignmentList) other;
        return internalList.equals(otherUniqueAssignmentList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code assignments} contains only unique assignments.
     */
    private boolean assignmentsAreUnique(List<Assignment> assignments) {
        for (int i = 0; i < assignments.size() - 1; i++) {
            for (int j = i + 1; j < assignments.size(); j++) {
                if (assignments.get(i).isSameAssignment(assignments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Sorts internalList by endDate
     */
    public void sortAssignments() {
        Comparator<Assignment> assignmentComparator = Comparator.comparing(Assignment::getEnd);
        internalList.sort(assignmentComparator);
    }
}
