package com.stayli.utils;

public class SingleLinkList<T> {


    // 定义头节点
    private Node<T> first;

    private Node<T> last;
    // 大小
    private int size;

    public SingleLinkList() {

    }

    // 默认尾部插入
    public void add(T t) {
        // 创建节点
        Node<T> newNode = new Node<>(null, t);

        Node<T> p = last;

        last = newNode;
        if (p == null) {
            first = newNode;
        } else {

            first.next = newNode;
        }
        size++;

    }


    private static class Node<T> {
        public Node next;
        public T data;


        public Node(Node next, T data) {
            this.next = next;
            this.data = data;
        }
    }
}
