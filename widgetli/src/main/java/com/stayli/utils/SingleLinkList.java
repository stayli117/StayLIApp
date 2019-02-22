package com.stayli.utils;

public class SingleLinkList<T> {


        // 定义头节点
        private Node<T> head;

        // 大小
        private int size;

        public SingleLinkList() {
            size = 0;
            head = null;
        }

        public int size() {
            return size;
        }

        // 默认尾部插入
        public void add(T t) {
            // 创建节点
            Node<T> newNode = new Node<>(t);
            add(newNode);
        }

        // 默认尾部插入
        public void add(Node<T> newNode) {
            if (size == 0) { // 第一次插入节点 即为头节点
                head = newNode;
            } else {
                // 找到最后一个节点插入
                Node<T> current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;

            }

            // 如果next属性暴露 为避免size出错 需继续进行size的增加
            while (newNode.next != null) {
                newNode = newNode.next;
                size++;
            }

            size++;
        }

        // 插入到指定位置
        public void insert(int index, Node<T> newNode) {
            if (index < 0 || index > size) throw new IndexOutOfBoundsException();

            if (index == 0) {//头节点替换即可
                newNode.next = head;
                head = newNode;
            } else {
                Node<T> current = head;
                Node<T> previous = head;
                for (int i = 0; i < index; i++) {
                    previous = current;
                    current = current.next;
                }
                previous.next = newNode;
                newNode.next = current;
            }

            size++;
        }

        // 插入到指定位置
        public void insert(int index, T t) {
            if (index < 0 || index > size) throw new IndexOutOfBoundsException();
            Node<T> newNode = new Node<>(t);
            insert(index, newNode);
        }


        /**
         * 获取指定索引位置上的数据
         *
         * @param index 指定索引
         * @return 数据
         */
        public T get(int index) {
            Node<T> current = getNode(index);
            return current.data;
        }


        /**
         * @param index 指定索引
         * @return 节点
         */
        public Node<T> getNode(int index) {
            if (index >= size || index < 0) throw new IndexOutOfBoundsException();

            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        }

        // 删除指定索引上的节点
        public T remove(int index) {
            if (index >= size) throw new IndexOutOfBoundsException();
            if (index == 0) { // 删除头节点
                size--;
                T data = head.data;
                head = head.next;
                return data;
            }

            Node<T> previous = head;
            // 获取当前要删除的节点
            Node<T> current = head;
            int len = size - index - 1;
            for (int i = 0; i < len; i++) {
                previous = current;
                current = current.next;
            }
            // 获取数据
            T data = current.data;
            // 设置删除节点的上个节点指向删除节点的下个节点
            previous.next = current.next;
            size--;
            return data;

        }

        // 删除节点
        public void remove(Node<T> node) {
            Node<T> current = head;
            if (current.equals(node)) { // 删除的为头节点
                head = current.next;
                size--;
                return;
            }
            Node<T> previous = head;
            while (current.next != null) {
                previous = current;
                current = current.next;
                if (current.equals(node)) {
                    previous.next = current.next;
                    break;
                }
            }
            size--;
        }


        //显示节点信息
        public void display() {
            if (size > 0) {
                Node node = head;
                int tempSize = size;
                if (tempSize == 1) {//当前链表只有一个节点
                    System.out.println("[" + node.data + "]");
                    return;
                }
                while (tempSize > 0) {
                    if (node.equals(head)) {
                        System.out.print("[" + node.data + "->");
                    } else if (node.next == null) {
                        System.out.print(node.data + "]");
                    } else {
                        System.out.print(node.data + "->");
                    }
                    node = node.next;
                    tempSize--;
                }
                System.out.println();
            } else {//如果链表一个节点都没有，直接打印[]
                System.out.println("[]");
            }

        }

        public void display(String tag) {
            System.out.println(tag);
            display();
        }

    public static class Node<T> {
        private Node<T> next;
        public T data;

        public Node(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Node) {
                Node<T> node = (Node<T>) obj;
                if (data.equals(node.data)) return true;

            }
            return false;
        }
    }
}
