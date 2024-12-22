package com.cgvsu.Math;

import com.cgvsu.math.Point2f;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Point2fTest {

    @Test
    void testConstructorWithCoordinates() {
        // Создаем точку с координатами (1, 2)
        Point2f point = new Point2f(1, 2);

        // Проверяем значения координат
        assertEquals(1, point.x, "X coordinate is incorrect");
        assertEquals(2, point.y, "Y coordinate is incorrect");
    }

    @Test
    void testConstructorWithArray() {
        // Массив координат
        float[] coordinates = {3, 4};

        // Создаем точку с массивом координат
        Point2f point = new Point2f(coordinates);

        // Проверяем значения координат
        assertEquals(3, point.x, "X coordinate is incorrect");
        assertEquals(4, point.y, "Y coordinate is incorrect");
    }

    @Test
    void testCopyConstructor() {
        // Создаем исходную точку
        Point2f original = new Point2f(5, 6);

        // Создаем копию точки
        Point2f copy = new Point2f(original);

        // Проверяем значения координат копии
        assertEquals(5, copy.x, "X coordinate of the copy is incorrect");
        assertEquals(6, copy.y, "Y coordinate of the copy is incorrect");
    }

    @Test
    void testDistanceSquared() {
        // Первая точка
        Point2f p1 = new Point2f(0, 0);

        // Вторая точка
        Point2f p2 = new Point2f(3, 4);

        // Вычисляем квадрат расстояния
        float result = p1.distanceSquared(p2);

        // Проверяем результат
        assertEquals(25, result, "Incorrect distance squared");
    }

    @Test
    void testDistance() {
        // Первая точка
        Point2f p1 = new Point2f(0, 0);

        // Вторая точка
        Point2f p2 = new Point2f(3, 4);

        // Вычисляем расстояние
        float result = p1.distance(p2);

        // Проверяем результат
        assertEquals(5, result, "Incorrect distance");
    }

    @Test
    void testDistanceL1() {
        // Первая точка
        Point2f p1 = new Point2f(0, 0);

        // Вторая точка
        Point2f p2 = new Point2f(3, 4);

        // Вычисляем расстояние L1
        float result = p1.distanceL1(p2);

        // Проверяем результат
        assertEquals(7, result, "Incorrect L1 distance");
    }

    @Test
    void testDistanceLinf() {
        // Первая точка
        Point2f p1 = new Point2f(0, 0);

        // Вторая точка
        Point2f p2 = new Point2f(3, 4);

        // Вычисляем расстояние Linf
        float result = p1.distanceLinf(p2);

        // Проверяем результат
        assertEquals(4, result, "Incorrect Linf distance");
    }
}