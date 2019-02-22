package com.stayli.utils;

import java.util.Scanner;

/**
 * 使用一维数组编码实现一个栈（Stack）类，
 * 要求提供以下操作：
 * （1）boolean isEmpty()：判断栈当前是否为空；
 * （2）入栈操作void push(obj)：把数据元素obj插入堆栈；
 * （3）出栈操作Object pop()：出栈，并返回删除的数据元素；
 * （4）Object getTop()：取堆栈当前栈顶的数据元素并返回。
 * 编写代码测试所形成的Stack类，
 * 然后利用Stack类实现以下功能：输入一个正整数，输出该整数所对应的二进制数。
 */
public class Stack<E> {

    private int size = 0;
    private E data;
    private E[] stacks;

    private int index;

    public Stack() {
        stacks = (E[]) new Object[16];
        for (int i = 0; i < stacks.length; i++) {
            stacks[i] = null;
        }
        index = stacks.length - 1;
    }

    public Stack(int size) {
        stacks = (E[]) new Object[size];
        for (int i = 0; i < stacks.length; i++) {
            stacks[i] = null;
        }
        index = stacks.length - 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    // 每次push都放入数组的最后一个索引位置 提高效率
    public void push(E e) {
        if (index < 0) {
            // 扩容
            throw new IllegalStateException();
        }
        stacks[index] = e;
        index--;
        size++;

    }


    // 每次弹出都弹出数组从前向后第一个不为null的数据
    public E pop() {
        int left = index + 1;
        if (left < 0) throw new IllegalStateException();
        E e = stacks[left];
        if (e != null) {
            stacks[left] = null;
            index++;
            size--;
            return e;
        }
        return data;
    }


    public E getTop() {
        int left = index + 1;
        return stacks[left];
    }

    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();// 初始化对象，并分配100空间
        Scanner sc = new Scanner(System.in);
        int num;
        System.out.print("Please input the number:");
        while (sc.hasNextInt()) {//循环运行；
            num = sc.nextInt();
            while (num > 0) { // 将输入的数以二进制的形式压入栈中；
                st.push(num % 2);
                num = num / 2;
            }
            System.out.print("The number is:");
            while (!st.isEmpty())// 输出二进制数；
            {
                System.out.print(st.getTop());
                st.pop();
            }
        }
        System.out.print("\n");
    }
}

