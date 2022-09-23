package com.qf.data.student.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * author 14526
 * create_time 2022/9/24
 */
@Documented
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyValidHandler.class)
public @interface MyValid {
    String message();
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}
