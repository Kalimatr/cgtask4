package com.cgvsu.AffineTransformTest;


import com.cgvsu.Affinetransform.*;
import com.cgvsu.math.Vector3f;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TranslateRotateScaleTest {
    @Test
    public void testScale() {
        Vector3f expectedVec = new Vector3f(2, 3, 5);

        AffineTransformation affineTransformation = new Scaling(2, 3, 5);
        Vector3f vec = new Vector3f(1, 1, 1);

        Assertions.assertTrue(expectedVec.equals(affineTransformation.transform(vec)));
    }

    @Test
    public void testTranslate() {
        Vector3f expectedVec = new Vector3f(9, -5, 2);

        AffineTransformation affineTransformation = new Translator(9, -5, 2);
        Vector3f vec = new Vector3f(0, 0, 0);

        Assertions.assertTrue(expectedVec.equals(affineTransformation.transform(vec)));
    }

    @Test
    public void testDefaultTransform() {
        Vector3f expectedVec = new Vector3f(9, -5, 2);

        AffineTransformation affineTransformation = new Transformation();
        Vector3f vec = new Vector3f(9, -5, 2);

        Assertions.assertTrue(expectedVec.equals(affineTransformation.transform(vec)));

    }

    @Test
    public void testScaleTransform() {
        Vector3f expectedVec = new Vector3f(9, -5, 2);

        AffineTransformation affineTransformation = new Transformation(new Scaling(9, -5, 2));
        Vector3f vec = new Vector3f(1, 1, 1);

        Assertions.assertTrue(expectedVec.equals(affineTransformation.transform(vec)));
    }

    @Test
    public void testRotateTransform() {
        Vector3f expectedVec = new Vector3f(1, -1, 1);

        AffineTransformation affineTransformation = new Transformation(
                new Rotator(90, Rotator.Axis.X),
                new Rotator(90, Rotator.Axis.Y),
                new Rotator(90, Rotator.Axis.Z));
        Vector3f vec = new Vector3f(1, 1, 1);

        Assertions.assertTrue(expectedVec.equals(affineTransformation.transform(vec)));

    }
    public boolean equalsEpsilon(Vector3f vec1,Vector3f vec2, float epsilon) {
        if(!(Math.abs(vec1.x - vec2.x) < epsilon
                && Math.abs(vec1.y - vec2.y) < epsilon
                && Math.abs(vec1.z - vec2.z) < epsilon)){
            return false;
        }
        return true;
    }

    @Test
    public void testTranslateTransform() {
        Vector3f expectedVec = new Vector3f(9, -5, 2);

        AffineTransformation affineTransformation = new Transformation(new Translator(9, -5, 2));
        Vector3f vec = new Vector3f(0, 0, 0);

        Assertions.assertTrue(expectedVec.equals(affineTransformation.transform(vec)));

    }
}
