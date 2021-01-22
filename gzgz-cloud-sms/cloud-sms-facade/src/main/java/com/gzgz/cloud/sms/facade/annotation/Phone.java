package com.gzgz.cloud.sms.facade.annotation;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.Length;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @ClassName: Phone
 * @Description: 比较好的参考 ：https://github.com/VincentSit/ChinaMobilePhoneNumberRegex/blob/master/README-CN.md
 * @Author: pzl
 * @CreateDate: 2021/1/8 16:45
 * @Version: 1.0
 */

@ConstraintComposition(CompositionType.OR)
@Pattern(regexp = "^1[3|4|5|7|8|9][0-9]{9}$")
@Null
@Length(min = 0, max = 0)
@Documented
@Constraint(validatedBy = {})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface Phone {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
