package com.example.hyr.startv.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Description:
 * 作者：hyr on 2016/11/23 10:23
 * 邮箱：2045446584@qq.com
 */
public class FloatingVideoButton extends FloatingActionButton {

    private RotatingProgressDrawable coverDrawable;
    private int percent, color;
    private ColorStateList backgroundHint;
    private float progress = 0f;

    public FloatingVideoButton(Context context) {
        super(context);
    }


    public FloatingVideoButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public FloatingVideoButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        setMaxImageSize();
    }


    private void setMaxImageSize() {

        try {
            Class clazz = getClass().getSuperclass();
            Method sizeMethod = clazz.getDeclaredMethod("getSizeDimension");
            sizeMethod.setAccessible(true);
            int size = (Integer) sizeMethod.invoke(this);
            Field field = clazz.getDeclaredField("mMaxImageSize");
            field.setAccessible(true);
            field.set(this, size);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        postInvalidate();


    }

    public void config(int percent, int color, ColorStateList backgroundHint) {
        this.percent = percent;
        this.color = color;
        this.backgroundHint = backgroundHint;
        config();
    }


    public void config() {
        if (coverDrawable != null) {
            coverDrawable.setProgressWidthPercent(percent);
            coverDrawable.setProgressColor(color);
            if (backgroundHint != null) {
                setBackgroundTintList(backgroundHint);
            }
        }
    }


    public void setProgress(float progress) {
        this.progress = progress;
        if (coverDrawable != null) {
            coverDrawable.setProgress(progress);
        }
    }

    public void setCoverDrawable(Drawable drawable) {
        this.coverDrawable = new RotatingProgressDrawable(drawable);
        config();
        setImageDrawable(this.coverDrawable);
    }

    public void setCoverDrawable(RotatingProgressDrawable drawable) {
        this.coverDrawable = drawable;
        config();
        setImageDrawable(this.coverDrawable);
    }

    public void setCover(Bitmap bitmap) {
        coverDrawable = new RotatingProgressDrawable(bitmap);
        config();
        setImageDrawable(this.coverDrawable);
    }

    public RotatingProgressDrawable getCoverDrawable() {
        return coverDrawable;
    }

    public void start() {
        coverDrawable.rotate(true);
    }

    public void stop() {
        coverDrawable.rotate(false);
    }
}
