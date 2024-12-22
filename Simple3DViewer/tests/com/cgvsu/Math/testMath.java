package com.cgvsu.Math;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.cgvsu.math.Vector2f;

public class testMath {
    @Test
    //Дописать тесты
    public void test1(){
        Vector2f v= new Vector2f(3, 4);
        final Vector2f result = v.multiply(4);
        final Vector2f expectedResult = new Vector2f(12, 16);
        Assertions.assertTrue(result.equals(expectedResult));
    }
}
