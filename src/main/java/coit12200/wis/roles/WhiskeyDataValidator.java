package coit12200.wis.roles;

public class WhiskeyDataValidator {
    public record Range(int lower, int upper) {}
    public record RangeValidationResponse(boolean result, Range r, String message){}

    public void WhiskeyValidator()
    {
    }

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

    public ValidationResponse checkRegion(String r) {
        if (r == null || r.trim().isEmpty()) {
            return new ValidationResponse(false, "Region can't be empty.");
        }
        return new ValidationResponse(true, "");
    }
}