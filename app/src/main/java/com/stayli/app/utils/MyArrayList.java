package com.stayli.app.utils;

public class MyArrayList<T> {


    private static final int DEFAULT_SIZE = 10;

    private Object[] items;
    private int size;

    private static final Object[] DEFAULT_ITEMS = new Object[DEFAULT_SIZE];

    public MyArrayList() {

        size = DEFAULT_SIZE;
        items = DEFAULT_ITEMS;
    }

    private void expandSize(int size) {

        int len = items.length;
        if (size > len) {
            Object[] old = items;
            items = new Object[len + (len >> 1)];
            System.arraycopy(old, 0, items, 0, size);
        }

    }

    public void add(T t) {
        //判断是否扩容
        expandSize(size + 1);
        items[size++] = t;

    }

    public void add(int index, T t) {
        if (index > size || index < 0) throw new ArrayIndexOutOfBoundsException();
        expandSize(size + 1);

        System.arraycopy(this.items, index, this.items, index + 1, this.size - index);

        items[index] = t;
        ++size;

    }


    public T remove(int index) {
        if (index > size || index < 0) throw new ArrayIndexOutOfBoundsException();

        int var = size - index - 1;
        if (var > 0) {
            System.arraycopy(items, index + 1, items, index, var);
        }

        size--;
        return (T) items[index];
    }


}
