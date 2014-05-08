/**
 * 
 */
package checkers.inference.reim.quals;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import checkers.quals.SubtypeOf;
import checkers.quals.TypeQualifier;
import checkers.quals.Unqualified;

/**
 * @author Wei Huang
 * @date Feb 17, 2011
 */
@Documented
@TypeQualifier
@Inherited
@SubtypeOf({I.class})
//@SubtypeOf({DefaultAnnotation.class})
//@Retention(RetentionPolicy.RUNTIME)
//@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
//@ImplicitFor(
//    treeClasses={LiteralTree.class},
//    typeClasses={AnnotatedPrimitiveType.class})
public @interface P {
    
}
