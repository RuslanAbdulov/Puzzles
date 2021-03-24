package com.company.reverse_linked_list;

import java.util.LinkedList;
import java.util.StringJoiner;

//напишите односвязный список, напечатайте и разверните его без обратных ссылок
class ODList<T> {

    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    public ODList() {
        this.size = 0;
    }

    public T next() {
        if (head == null) {
            return null;
        }

        return head.getValue();
    }

    public Node<T> head() {
        return head;
    }

    public ODList<T> add(T newValue) {
        Node<T> node = new Node<>(null, newValue);
        if (tail == null) {
            tail = node;
            head = tail;
        } else {
            tail.setNext(node);
            tail = node;
        }
        size++;

        return this;
    }

    public ODList<T> reverse() {
        LinkedList<T> stack = new LinkedList<>();

        Node<T> node = head;
        while(node != null) {
            stack.push(node.value);
            node = node.next();
        }

        ODList<T> reverse = new ODList<>();
        while(!stack.isEmpty()) {
            reverse.add(stack.pop());
        }
        return reverse;
    }

    public void naiveReverse() {
        int size = 0;

        Node<T> cursor = head;
        while(cursor != null) {
            size++;
            cursor = cursor.next();
        }

        for (int i = size - 1; i >= 0 ; i--) {
            cursor = head;
            for (int j = 0; j < i; j++) {
                cursor = cursor.next();
            }
            System.out.println(cursor.getValue());
        }


    }

    public int getSize() {
        return size;
    }

    static class Node<T> {
        private Node<T> next;
        private T value;

        public Node(T value) {
            this.value = value;
        }

        public Node(Node<T> next, T value) {
            this.next = next;
            this.value = value;
        }

        public Node<T> next() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", ODList.class.getSimpleName() + "[", "]");
        Node<T> node = head;
        while(node != null) {
            joiner.add(node.getValue().toString());
            node = node.next();
        }

        return joiner.toString();
    }
}
