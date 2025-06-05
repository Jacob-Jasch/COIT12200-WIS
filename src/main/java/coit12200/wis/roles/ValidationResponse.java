package coit12200.wis.roles;

/**
 * ValidationResponse is a record that shows the result of a validation operation.
 * @param result indicates whether the validation was successful or not
 * @param message provides additional information about the validation result
 */
public record ValidationResponse(boolean result, String message) {
}
