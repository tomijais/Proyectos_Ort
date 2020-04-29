package com.ejemplos.objectsexample;

public class Mesa
{
    private double altura;
    private String color;
    private int cantidadPatas;

    public Mesa(double altura, String color, int cantidadPatas)
    {
        this.altura = altura;
        this.color = color;
        this.cantidadPatas = cantidadPatas;
    }


    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
