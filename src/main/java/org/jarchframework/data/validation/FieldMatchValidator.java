package org.jarchframework.data.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.jarchframework.core.util.UtilsForReflection;

public final class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
	private String firstFieldName;
	private String secondFieldName;
	private String errorMessage;

	public void initialize(final FieldMatch constraintAnnotation) {
		firstFieldName = constraintAnnotation.first();
		secondFieldName = constraintAnnotation.second();
		errorMessage = constraintAnnotation.message();
	}

	public boolean isValid(final Object value, final ConstraintValidatorContext cvc) {

		boolean valid = false;
		Object firstObj = UtilsForReflection.getValue(value, firstFieldName);
		Object secondObj = UtilsForReflection.getValue(value, secondFieldName);
		valid = (firstObj == null && secondObj == null) || (firstObj != null && firstObj.equals(secondObj));

		if (!valid) {
			cvc.disableDefaultConstraintViolation();
			cvc.buildConstraintViolationWithTemplate(errorMessage).addPropertyNode(firstFieldName)
					.addConstraintViolation();
		}
		return valid;
	}
}