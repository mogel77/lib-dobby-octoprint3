package de.x8games.octolib3.annotations;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface OPiValue {

    boolean MayBeMissing() default false;

}
