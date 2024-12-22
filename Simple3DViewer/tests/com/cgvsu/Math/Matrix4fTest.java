package com.cgvsu.Math;

import com.cgvsu.math.Matrix4f;
import com.cgvsu.math.Vector4f;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@Nested
class Matrix4fTest {

    private Matrix4f identityMatrix;
    private Matrix4f matrixA;
    private Matrix4f matrixB;

    @BeforeEach
    void setUp() {
        identityMatrix = new Matrix4f(1.0f);
        matrixA = new Matrix4f(
                new float[][]{
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 16}
                });
        matrixB = new Matrix4f(
                new float[][]{
                        {-1, -2, -3, -4},
                        {-5, -6, -7, -8},
                        {-9, -10, -11, -12},
                        {-13, -14, -15, -16}
                });
    }

    @Test
    void constructorWithArrayShouldThrowExceptionIfInvalidSize() {
        assertThrows(IllegalArgumentException.class, () ->
                new Matrix4f(new float[][]{{1, 2}, {3, 4}})
        );
    }

    @Test
    void constructorWithFloatShouldCreateDiagonalMatrix() {
        Matrix4f diagonalMatrix = new Matrix4f(2.0f);
        assertAll(
                () -> assertEquals(2.0f, diagonalMatrix.mat[0][0]),
                () -> assertEquals(2.0f, diagonalMatrix.mat[1][1]),
                () -> assertEquals(2.0f, diagonalMatrix.mat[2][2]),
                () -> assertEquals(2.0f, diagonalMatrix.mat[3][3])
        );
    }

    @Test
    void copyConstructorShouldCreateIndependentCopy() {
        Matrix4f copiedMatrix = new Matrix4f(matrixA.cloneMatrix());
        matrixA.mat[0][0] = 100.0f;
        assertNotEquals(copiedMatrix.mat[0][0], matrixA.mat[0][0]);
    }

    @Test
    void addMethodShouldCorrectlyAddMatrices() {
        Matrix4f sum = Matrix4f.add(matrixA.cloneMatrix(), matrixB.cloneMatrix());
        assertAll(
                () -> assertEquals(0.0f, sum.mat[0][0]),
                () -> assertEquals(0.0f, sum.mat[1][1]),
                () -> assertEquals(0.0f, sum.mat[2][2]),
                () -> assertEquals(0.0f, sum.mat[3][3])
        );
    }

    @Test
    void subMethodShouldCorrectlySubtractMatrices() {
        Matrix4f difference = Matrix4f.sub(matrixA.cloneMatrix(), matrixB.cloneMatrix());
        Matrix4f result = new Matrix4f(
                new float[][]{
                        {2, 4, 6, 8},
                        {10, 12, 14, 16},
                        {18, 20, 22, 24},
                        {26, 28, 30, 32}
                }
        );
        assertTrue(difference.equals(result));
    }

    @Test
    void multiplyMethodShouldCorrectlyMultiplyMatrices() {
        Matrix4f product = Matrix4f.multiply(matrixA.cloneMatrix(), matrixB.cloneMatrix());
        Matrix4f result = new Matrix4f(
                new float[][]{
                        {-90, -100, -110, -120},
                        {-202, -228, -254, -280},
                        {-314, -356, -398, -440},
                        {-426, -484, -542, -600}
                }
        );

        assertTrue(product.equals(result));
    }

    @Test
    void mulVectorMethodShouldCorrectlyMultiplyMatrixByVector() {
        Vector4f vector = new Vector4f(1, 2, 3, 4);
        Vector4f result = matrixA.cloneMatrix().mulVector(vector);
        assertAll(
                () -> assertEquals(30.0f, result.x),
                () -> assertEquals(70.0f, result.y),
                () -> assertEquals(110.0f, result.z),
                () -> assertEquals(150.0f, result.w)
        );
    }

    @Test
    void transposeMethodShouldTransposeTheMatrix() {
        Matrix4f transposedMatrix = matrixA.cloneMatrix();
        transposedMatrix.transpose();
        assertAll(
                () -> assertEquals(1.0f, transposedMatrix.mat[0][0]),
                () -> assertEquals(5.0f, transposedMatrix.mat[0][1]),
                () -> assertEquals(9.0f, transposedMatrix.mat[0][2]),
                () -> assertEquals(13.0f, transposedMatrix.mat[0][3])
        );
    }

    @Test
    void determinantMethodShouldCalculateDeterminantCorrectly() {
        float det = matrixA.cloneMatrix().determinant();
        assertEquals(0.0f, det, 0.0001f);
    }

    @Test
    void equalsMethodShouldCompareMatricesCorrectly() {
        Matrix4f sameMatrix = matrixA.cloneMatrix();
        assertTrue(matrixA.equals(sameMatrix));
    }
}


