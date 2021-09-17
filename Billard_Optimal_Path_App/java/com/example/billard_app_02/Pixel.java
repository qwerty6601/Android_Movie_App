package com.example.billard_app_02;

public class Pixel {
    private int x;
    private int y;
    public Pixel(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getIndex(int colNum) {
        int index = colNum * y + x;
        return index;
    }

    public String toString(){
        return  x+ " " +y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
