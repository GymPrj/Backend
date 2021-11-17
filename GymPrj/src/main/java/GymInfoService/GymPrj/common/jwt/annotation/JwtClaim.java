package GymInfoService.GymPrj.common.jwt.annotation;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface JwtClaim {

    String value() default "";
}
