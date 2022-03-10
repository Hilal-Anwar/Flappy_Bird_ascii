package org.flappy_bird.game;

import java.io.IOException;

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
         StringBuilder s= new StringBuilder("=>");
         while (s.length()<=65){
             System.out.println("""





                     """);
             System.out.println(text.indent(25));
             s.insert(0, "=");
             System.out.println(s.toString().indent(25));
             Thread.sleep(10);
             cls();
         }
         var game=new Game();
         game.start();
    }
    static void cls() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
