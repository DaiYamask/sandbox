package com.example.reactorplayground;

import java.util.List;

/**
 * @author DAI Yamasaki
 */
public interface MyEventListener<T> {
    void onDataChunk(List<T> chunk);
    void processComplete();
}
