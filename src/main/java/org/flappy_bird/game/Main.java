package org.flappy_bird.game;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        int width, height;
        var in = new Scanner(System.in);
        System.out.println("Welcome to Flappy Bird");
        System.out.println("Please enter the width and height");
        width = in.nextInt();
        height = in.nextInt();
        var game = new Game(width, height);
        game.start();
    }

}
