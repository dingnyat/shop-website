package me.annanjin.shop.utils;

public class WrapObject<T, U> {
    private T firstObject;
    private U secondObject;

    public WrapObject() {
    }

    public WrapObject(T firstObject, U secondObject) {
        this.firstObject = firstObject;
        this.secondObject = secondObject;
    }

    public T getFirstObject() {
        return firstObject;
    }

    public void setFirstObject(T firstObject) {
        this.firstObject = firstObject;
    }

    public U getSecondObject() {
        return secondObject;
    }

    public void setSecondObject(U secondObject) {
        this.secondObject = secondObject;
    }
}
