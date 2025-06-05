package coit12200.wis.roles;

/**
 * WhiskeyDataValidator class provides methods to validate whiskey data.
 * It checks the age range and region for validity.
 * @author Jacob Duckworth
 */
public class WhiskeyDataValidator {
    /**
     * Represents a range with lower and upper bounds.
     * @param lower the lower bound of the range
     * @param upper the upper bound of the range
     */
    public record Range(int lower, int upper) {}

    /**
     * Response for validation checks. 
     * @param result indicates if the validation passed or failed
     * @param r the range object if applicable
     * @param message provides additional information about the validation result
     */
    public record RangeValidationResponse(boolean result, Range r, String message){}

    /**
     * Constructor for WhiskeyDataValidator class.
     */
    public void WhiskeyValidator()
    {
    }

    /**
     * Checks if the age range is valid.
     * It ensures that both ages are positive integers and that the lower age is less than or equal to the upper age.
     * @param b1 the lower age to validate as a string
     * @param b2 the upper age to validate as a string
     * @return RangeValidationResponse indicating the result of the validation.
     */
    public RangeValidationResponse checkAgeRange(String b1, String b2) {
        try {
            int lower = Integer.parseInt(b1);
            int upper = Integer.parseInt(b2);
            if (lower < 0 || upper < 0) {
                return new RangeValidationResponse(false, null, "Ages must be positive numbers.");
            }
            if (lower > upper) {
                return new RangeValidationResponse(false, null, "Lower age must be less than the higher age.");
            }
            return new RangeValidationResponse(true, new Range(lower, upper), "");
        } catch (NumberFormatException e) {
            return new RangeValidationResponse(false, null, "Both ages must be a number.");
        }
    }

    /**
     * Checks if the region is valid.
     * It ensures that the region is not null or empty.
     * @param r the region to validate as a string
     * @return ValidationResponse indicating the result of the validation.
     */
    public ValidationResponse checkRegion(String r) {
        if (r == null || r.trim().isEmpty()) {
            return new ValidationResponse(false, "Region can't be empty.");
        }
        return new ValidationResponse(true, "");
    }
}