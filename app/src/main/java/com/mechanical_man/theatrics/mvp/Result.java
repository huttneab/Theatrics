package com.mechanical_man.theatrics.mvp;

/**
 * Created by Mechanical Man, LLC on 3/5/17. Theatrics
 */

public final class Result<T> {

    public static <T> Result<T> from(T value) {
        return new Result<>(value, null);
    }

    public static <T> Result<T> error(Throwable throwable) {
        return new Result<>(null, throwable);
    }

    private final T value;
    private final Throwable error;

    private Result(T value, Throwable throwable) {
        this.value = value;
        this.error = throwable;
    }

    public boolean isSuccess() {
        return error == null;
    }

    public T value() {
        return value;
    }

    public Throwable error() {
        return error;
    }

    @Override
    public String toString() {
        return "Result{" +
                "value=" + value +
                ", error=" + error +
                '}';
    }
}
