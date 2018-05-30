package be.vdab.personeel.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target( {TYPE, ANNOTATION_TYPE} )
@Constraint(validatedBy = RijksregisternummerValidator.class)
public @interface Rijksregisternummer {

	String message() default "{be.vdab.proefpakket.constraints.Rijksregisternummer.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
}
