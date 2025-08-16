package org.flappy_bird.game;


import java.io.IOException;
import java.util.concurrent.Executors;

public class KeyBoardInput {
     private Key keyBoardKey=Key.NONE;
    public KeyBoardInput(Display display) {
        Executors.newVirtualThreadPerTaskExecutor().execute(()->{
            while (true) {
                try {
                    setKeyBoardKey(getKeys(display.terminal.reader().read()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        /*Thread.startVirtualThread(() -> {

        }).start();*/
    }
    private static Key getKeys(int ch) {
        return switch (ch) {
            case 9->   Key.TAB;
            case 13->  Key.ENTER;
            case 27->  Key.ESC;
            case 8->   Key.BACKSPACE;
            case 65 -> Key.UP;
            case 32->  Key.SPACE;
            case 66 -> Key.DOWN;
            case 68 -> Key.LEFT;
            case 67 -> Key.RIGHT;
            case 100-> Key.DROP;
            case 104-> Key.HOLD;
            default -> Key.NONE;
        };
    }

    public Key getKeyBoardKey() {
        return keyBoardKey;
    }

    public void setKeyBoardKey(Key keyBoardKey) {

        this.keyBoardKey = keyBoardKey;
    }
}
