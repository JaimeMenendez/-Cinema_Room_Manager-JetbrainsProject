package com.company;

class Cinema {
    private final int rows;
    private final int columns;
    private final char[][] seats;
    public boolean sellingSeats = true;
    private int income = 0;

    public Cinema(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.seats = new char[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                seats[i][j] = 'S';
            }
        }
    }

    public void showOptions() {
        System.out.println("""
                1. Show the seats
                2. Buy a ticket
                3. Statistics
                0. Exit""");
    }

    public void showSeats() {
        System.out.print("\nCinema:\n ");
        for (int i = 1; i <= columns; i++) {
            System.out.print(" " + i);
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= columns; j++) {
                if (j == 0) {
                    System.out.print((i + 1) + " ");
                } else {
                    System.out.print(seats[i][j - 1] + " ");
                }
            }
            System.out.println();
        }
    }

    public int availableSeats() {
        int seatsAvailable = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                seatsAvailable += seats[i][j] == 'S' ? 1 : 0;
            }
        }
        return seatsAvailable;
    }

    public void statistics() {
        int totalSeats = rows * columns;
        int seatsAvailable = availableSeats();
        int purchasedTickets = totalSeats - seatsAvailable;
        double percentage = (double) purchasedTickets / totalSeats * 100;

        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
        System.out.println("Current income: $" + income);
        System.out.println("Total income: $" + totalIncome());
    }

    private int seatPrice(int seatRow) {
        int totalSeats = rows * columns;
        if (totalSeats <= 60) {
            return 10;
        } else {
            int rowLimit = rows % 2 == 0 ? rows / 2 : (rows + 1) / 2;
            if (seatRow < rowLimit) {
                return 10;
            } else {
                return 8;
            }
        }
    }

    public boolean buyTicked(int seatRow, int seatColumn) {
        if (seatRow <= 0 || seatRow > rows || seatColumn <= 0 || seatColumn > columns) {
            System.out.println("Wrong input");
            return false;
        } else if (seats[seatRow - 1][seatColumn - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");
            return false;
        } else {
            seats[seatRow - 1][seatColumn - 1] = 'B';
            int tickedPrice = seatPrice(seatRow);
            income += tickedPrice;
            System.out.println("Ticket price: $" + tickedPrice);
            return true;
        }
    }

    public int totalIncome() {
        int totalSeats = rows * columns;
        if (totalSeats <= 60) {
            return 10 * rows * columns;
        } else {
            int rowLimit = rows % 2 == 0 ? rows / 2 : (rows + 1) / 2;
            return ((rowLimit - 1) * 10 + rowLimit * 8) * columns;
        }
    }
}

