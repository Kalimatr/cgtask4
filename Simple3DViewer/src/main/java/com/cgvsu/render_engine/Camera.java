package com.cgvsu.render_engine;


import com.cgvsu.Affinetransform.*;
import com.cgvsu.math.Matrix4f;
import com.cgvsu.math.Vector3f;

public class Camera {

    public Camera(
            final Vector3f position,
            final Vector3f target,
            final float fov,
            final float aspectRatio,
            final float nearPlane,
            final float farPlane) {
        this.position = position;
        this.target = target;
        this.fov = fov;
        this.aspectRatio = aspectRatio;
        this.nearPlane = nearPlane;
        this.farPlane = farPlane;
    }

    public void setPosition(final Vector3f position) {
        this.position = position;
    }

    public void setTarget(final Vector3f target) {
        this.target = target;
    }

    public void setAspectRatio(final float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getTarget() {
        return target;
    }

    public void movePosition(final Vector3f translation) {
        this.position.add(translation);
    }

    public void Rotate(float x, float y, float z) {
        AffineTransformation affineTransformation = new Transformation(
                new Rotator(x, Rotator.Axis.X),
                new Rotator(y, Rotator.Axis.Y),
                new Rotator(z, Rotator.Axis.Z));
        this.position = affineTransformation.transform(this.position);
    }

    public void Rotate(int x, int y, int z) {
        AffineTransformation affineTransformation = new Transformation(
                new Rotator(x, Rotator.Axis.X),
                new Rotator(y, Rotator.Axis.Y),
                new Rotator(z, Rotator.Axis.Z));
        this.position = affineTransformation.transform(this.position);
    }

    public void Scale(float x, float y, float z) {
        AffineTransformation affineTransformation = new Transformation(new Scaling(x, y, z));
        this.position = affineTransformation.transform(this.position);
    }

    public void Translate(float x, float y, float z) {
        AffineTransformation affineTransformation = new Translator(x, y, z);
        this.position = affineTransformation.transform(this.position);
    }

    public void moveTarget(final Vector3f translation) {
        this.target.add(target);
    }

    Matrix4f getViewMatrix() {
        return GraphicConveyor.lookAt(position, target);
    }

    Matrix4f getProjectionMatrix() {
        return GraphicConveyor.perspective(fov, aspectRatio, nearPlane, farPlane);
    }


    private Vector3f position;
    private Vector3f target;
    private float fov;
    private float aspectRatio;
    private float nearPlane;
    private float farPlane;
}