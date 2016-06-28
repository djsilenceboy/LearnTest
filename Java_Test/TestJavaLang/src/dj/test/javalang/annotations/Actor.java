
package dj.test.javalang.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Actor
{
	String name();

	int age() default 20;
}
