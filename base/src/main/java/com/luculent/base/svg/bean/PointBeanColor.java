package com.luculent.base.svg.bean;

/**
 * @Author byz
 * @CreateData 2020/11/25 11:38
 */
public class PointBeanColor {
    int r;
    String tag;
    int color;
    int x;
    int y;


    public PointBeanColor(int r, String tag, int color, int x, int y) {
        this.r = r;
        this.tag = tag;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
