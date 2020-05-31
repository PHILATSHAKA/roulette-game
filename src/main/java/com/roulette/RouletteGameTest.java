package com.roulette;

import java.io.File;
import java.io.IOException;

public class RouletteGameTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        RouletteGame rouletteGame = new RouletteGame();
        File file = rouletteGame.getFileFromResources("roulette_file.txt");
        rouletteGame.printFile(file);
        Thread t1 = new Thread(new RouletteGame.ParallelTask());
        t1.start();
        t1.join();
        Thread t2 = new Thread(new RouletteGame.ParallelTask());
        t2.start();
    }
}
