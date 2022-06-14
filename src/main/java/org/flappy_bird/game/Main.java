package org.flappy_bird.game;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static String text = """
                
                
                
                
                
                
                ____  ______ _                           ____  _         _  ____  \s
               / / / |  ____| |                         |  _ \\(_)       | | \\ \\ \\ \s
              / / /  | |__  | | __ _ _ __  _ __  _   _  | |_) |_ _ __ __| |  \\ \\ \\\s
             < < <   |  __| | |/ _` | '_ \\| '_ \\| | | | |  _ <| | '__/ _` |   > > >
              \\ \\ \\  | |    | | (_| | |_) | |_) | |_| | | |_) | | | | (_| |  / / /\s
               \\_\\_\\ |_|    |_|\\__,_| .__/| .__/ \\__, | |____/|_|_|  \\__,_| /_/_/ \s
                                    | |   | |     __/ |                           \s
                                    |_|   |_|    |___/                            \s
                                    
                                    
                                    
            """;

    public static void main(String[] args) throws InterruptedException, IOException {
        StringBuilder s = new StringBuilder("===>");
        int width, height;
        var in = new Scanner(System.in);
        System.out.println("Welcome to Flappy Bird");
        System.out.println("Please enter the width and height");
        width = in.nextInt();
        height = in.nextInt();
        loading(s);
        var game = new Game(width, height);
        game.start();
    }

    private static void loading(StringBuilder s) throws InterruptedException, IOException {
        while (s.length() <= 65) {
            System.out.println(text.indent(25));
            s.insert(0, "=");
            System.out.println(s.toString().indent(25));
            Thread.sleep(10);
            cls();
        }
    }

    static void cls() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
