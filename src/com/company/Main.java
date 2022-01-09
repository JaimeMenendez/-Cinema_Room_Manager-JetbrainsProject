package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int columns = scanner.nextInt();

        Cinema cinema = new Cinema(rows, columns);

        while (cinema.sellingSeats) {
            cinema.showOptions();
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> cinema.showSeats();
                case 2 -> {
                    int rowSeat, columnSeat;
                    do {
                        System.out.println("\nEnter a row number:");
                        rowSeat = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        columnSeat = scanner.nextInt();
                    } while (!cinema.buyTicked(rowSeat, columnSeat));
                }
                case 3 -> cinema.statistics();
                case 0 -> cinema.sellingSeats = false;
            }
        }
    }
}
