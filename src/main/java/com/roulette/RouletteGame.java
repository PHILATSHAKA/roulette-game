package com.roulette;

import java.io.*;
import java.net.URL;
import java.util.*;

class RouletteGame {
    public static class ParallelTask implements Runnable {
        @Override
        public void run() {
            try {
                game();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // get file from classpath, resources folder
    File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

    }

    // print the file
    void printFile(File file) throws IOException {

        if (file == null) return;

        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    private static int generateRandomNumber() throws InterruptedException {
        Random random = new Random();
        return random.nextInt(37);
    }

    private static void game() throws InterruptedException {
        Scanner userInput = new Scanner(System.in);
        double total = 500.0, amount, winnings = 0.0;
        int choice, number, result;
        String outcome, playerName, bet = "";
        final int[] rouletteNum = new int[1];
        char userResponse = 'y';
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    rouletteNum[0] = generateRandomNumber();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 30000);

        System.out.println("Enter player's name ");
        playerName = userInput.next();
        while (userResponse == 'y' || userResponse == 'Y' && total <= 0) {
            System.out.println("Enter your bet amount");
            amount = userInput.nextDouble();
            System.out.print("0 - Even\n1 - Odd\n2 - Number\n");
            choice = -1;
            while (choice < 0 || choice > 2) {
                System.out.println("Choose from the choices above to place your bet on");
                choice = userInput.nextInt();
            }
            number = 0;
            switch (choice) {
                case 2:
                    while (number < 1 || number > 36) {
                        System.out.println("Place your bet on number(1-36): ");
                        number = userInput.nextInt();
                        bet = String.valueOf(number);
                    }
                    break;
                case 1:
                    bet = "ODD";
                    break;
                case 0:
                    bet = "EVEN";
                    break;
            }
            if (choice == 2) {
                if (rouletteNum[0] == number)
                    result = 36;
                else
                    result = 0;
            } else {
                if (rouletteNum[0] == 0 || rouletteNum[0] % 2 != choice)
                    result = 0;
                else
                    result = 2;
            }

            // Print out game outcomes, win/lose amount
            if (result > 0) {
                winnings += result * amount;
                total = result * amount + total;
                outcome = "WIN";

            } else {
                total = total - result * amount;
                winnings += 0.0;
                outcome = "LOSE";
                if (total <= 0) {
                    break;
                }

            }

            Player players = new Player(playerName, bet, rouletteNum[0], outcome, winnings);
            players.displayPlayerPlayResults();

            System.out.println("\nWould you like to play another game? (y/n) ");
            userResponse = userInput.next().charAt(0);


        }
    }
}
