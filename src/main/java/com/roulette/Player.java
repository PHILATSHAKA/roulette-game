package com.roulette;

public class Player {
    private String playerName;
    private int rouletteNumber;
    private String choice;
    private String playerOutcome;
    private double playerWinnings;

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    Player(String playerName, String choice, int rouletteNumber, String playerOutcome, double playerWinnings) {
        this.playerName = playerName;
        this.choice = choice;
        this.playerOutcome = playerOutcome;
        this.playerWinnings = playerWinnings;
        this.rouletteNumber = rouletteNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getRouletteNumber() {
        return rouletteNumber;
    }

    public void setRouletteNumber(int rouletteNumber) {
        this.rouletteNumber = rouletteNumber;
    }

    public String getPlayerOutcome() {
        return playerOutcome;
    }

    public void setPlayerOutcome(String playerOutcome) {
        this.playerOutcome = playerOutcome;
    }

    public double getPlayerWinnings() {
        return playerWinnings;
    }

    public void setPlayerWinnings(double playerWinnings) {
        this.playerWinnings = playerWinnings;
    }
     public void displayPlayerPlayResults() {
         System.out.println("Number: " + getRouletteNumber());
         System.out.printf("%10s %15s %20s %25s \n", "Player", "Bet", "Outcome", "Winnings");
         System.out.println("-----------------------------------------------------------------------------");
         System.out.format("%10s %15s %20s %25s \n", getPlayerName(), getChoice(), getPlayerOutcome(), getPlayerWinnings());
     }
}
