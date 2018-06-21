package ru.track.list;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 * Односвязный список
 */
public class MyLinkedList extends List {

    /**
     * private - используется для сокрытия этого класса от других.
     * Класс доступен только изнутри того, где он объявлен
     * <p>
     * static - позволяет использовать Node без создания экземпляра внешнего класса
     */
    private static class Node {
        Node prev;
        Node next;
        int val;

        Node(Node prev, Node next, int val) {
            this.prev = prev;
            this.next = next;
            this.val = val;
        }
    }

    Node begin = null;
    Node end = null;
    int size = 0;

    @Override
    void add(int item) {
        if (begin == null){
            begin  = new Node(null, null, item);
            end = begin;
        }
        else{
            Node newNode = new Node(end, null, item);
            end.next = newNode;
            end = end.next;
        }
        size++;

    }

    @Override
    int remove(int idx) throws NoSuchElementException {
        if (idx >= size || idx < 0){
            throw new NoSuchElementException();
        }

        Node currentNode = begin;
        for (int i = 0; i < idx; i++){
            currentNode = currentNode.next;
        }

        int deleted = currentNode.val;

        if (currentNode != begin && currentNode != end){
            currentNode.prev.next = currentNode.next;
            currentNode.next.prev = currentNode.prev;
        }

        if (currentNode == begin){
            begin = begin.next;
            System.out.println(idx);
            if (size == 1){
                end = null;
            }
        }

        else if (currentNode == end){
            end = end.prev;
        }


        size--;
        return deleted;
    }

    @Override
    int get(int idx) throws NoSuchElementException {
        if (idx >= size || idx < 0){
            throw new NoSuchElementException();
        }

        Node currentNode = begin;
        for (int i = 0; i < idx; i++){
            currentNode = currentNode.next;
        }

        return currentNode.val;
    }

    @Override
    int size() {
        return size;
    }
}
