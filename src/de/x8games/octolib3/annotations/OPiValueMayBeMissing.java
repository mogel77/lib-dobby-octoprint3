package de.x8games.octolib3.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * es kann sein, das einige Variablen/Werte *generell* nicht verfügbar sind
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface OPiValueMayBeMissing {
}
