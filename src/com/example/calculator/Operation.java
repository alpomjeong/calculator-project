package com.example.calculator;

@FunctionalInterface
public interface Operation {
    double apply(int a, int b);
}