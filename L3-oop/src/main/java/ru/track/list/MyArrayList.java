package ru.track.list;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 *
 * Должен иметь 2 конструктора
 * - без аргументов - создает внутренний массив дефолтного размера на ваш выбор
 * - с аргументом - начальный размер массива
 */
public class MyArrayList extends List {

    int data[];
    int size = 0;
    int capacity = 0;
    final int DEFAULT_SIZE = 10;

    public MyArrayList() {
        this.data = new int[DEFAULT_SIZE];
        this.capacity = DEFAULT_SIZE;

    }

    public MyArrayList(int capacity) {
        this.data = new int[capacity];
        this.capacity = capacity;
    }

    @Override
    void add(int item) {
        if (size >= capacity){
            int newData[] = new int[capacity * 2 + 1];
            System.arraycopy(data, 0, newData, 0, capacity);
            capacity = capacity * 2 + 1;
            data = newData;
        }
        data[size] = item;
        size++;

    }

    @Override
    int remove(int idx) throws NoSuchElementException {
        if (idx >= size || idx < 0){
            throw new NoSuchElementException();
        }

        int deleted = data[idx];

        for(int i = idx +1; i < size; i++){
            data[i - 1] = data[i];
        }
        size--;
        return deleted;
    }

    @Override
    int get(int idx) throws NoSuchElementException {
        if (idx >= size || idx < 0){
            throw new NoSuchElementException();
        }
        return data[idx];
    }

    @Override
    int size() {
        return size;
    }
}
