package com.mechanical_man.theatrics;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Mechanical Man, LLC on 3/4/17. Theatrics
 */

@Qualifier @Retention(RUNTIME)
public @interface ForApplication {
}
