package com.luculent.base.svg.bean;

import android.graphics.Bitmap;

/**
 * @Author byz
 * @CreateData 2020/11/25 11:00
 */
public class PointBeanBitmap {
    int x;
    int y;
    String tag;
    Bitmap bitmap;

    public PointBeanBitmap(int x, int y, String tag, Bitmap bitmap) {
        this.x = x;
        this.y = y;
        this.tag = tag;
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
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
}
