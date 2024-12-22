package com.cgvsu.math;

public interface Vector<T extends Vector<T>> {

    void add(T v);
    void sub(T v);

    void sub(com.cgvsu.math.Vector3f var1, com.cgvsu.math.Vector3f var2);

    T multiply(float c);
    void mult(float c);

    T divide(float c);

    void div(float c);

    float length();

    T normal();

    boolean equals(T other);
}
