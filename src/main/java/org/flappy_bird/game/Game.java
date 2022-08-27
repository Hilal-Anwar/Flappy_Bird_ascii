package org.flappy_bird.game;

import org.jline.utils.InfoCmp;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;




public class Game {
    String TITLE = """
                
                
                
                
                
                
                ____  ______ _                           ____  _         _  ____  \s
               / / / |  ____| |                         |  _ \\(_)       | | \\ \\ \\ \s
              / / /  | |__  | | __ _ _ __  _ __  _   _  | |_) |_ _ __ __| |  \\ \\ \\\s
             < < <   |  __| | |/ _` | '_ \\| '_ \\| | | | |  _ <| | '__/ _` |   > > >
              \\ \\ \\  | |    | | (_| | |_) | |_) | |_| | | |_) | | | | (_| |  / / /\s
               \\_\\_\\ |_|    |_|\\__,_| .__/| .__/ \\__, | |____/|_|_|  \\__,_| /_/_/ \s
                                    | |   | |     __/ |                           \s
                                    |_|   |_|    |___/                            \s
                                    
                                    
                                    
            """;
    private final int width;
    private final int height;
    int score = 0;
    int level = 0;
    boolean condition = false;
    int speed = 2;
    private Obstacles obstacles;
    private Display display;
    private ArrayDeque<Block> ob;
    private KeyBoardInput keyBoardInput;
    private AnimateAsciiImage ani;
    private HashSet<Integer> memory = new HashSet<>();
    private BirdParts[] bird;
    private int MAX_LENGTH = 0;
    String b1= """
                __     __------
            ___/o `\\ ,~   _~~  .
            ~ -.   ,'   _~-----\s
                `\\     ~~~--_'__
                  `~-==-~~~~~---'
            """;
    String b2= """
                __            \s
            ___/o `\\           \s
            ~ -.   ,'\\~~~~~    \s
                `\\    ~~~~--_'__
                  `~-==-~~~~~---'
            """;

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public Tuple _getEntity(String name) {
        var list = name.split("\n");
        StringBuilder builder=new StringBuilder();
        var textList = new ArrayList<BirdParts>();
        for (int j = 0; j < list.length; j++) {
            String points = list[j];
            for (int i = points.length()-1; i >=0; i--) {
                int i1 = width / 3 +
                        (points.length() - 1 - i);
                if (points.charAt(i)=='/'){
                  textList.add(new BirdParts("" +'\\', new Point(i1, height / 2 + j)));
                  builder.append("" +'\\');
                }
                else if (points.charAt(i)=='\\'){
                    textList.add(new BirdParts("" + '/', new Point(i1, height / 2 + j)));
                    builder.append("" + '/');
                }
                else {
                    textList.add(new BirdParts("" + points.charAt(i), new Point(i1, height / 2 + j)));
                    builder.append(points.charAt(i));
                }
                MAX_LENGTH = Math.max(MAX_LENGTH, points.length() - 1);
            }
        }
        return new Tuple(builder.toString(),textList);
    }
    void start() throws InterruptedException {
        display=new Display();
        display.terminal.puts(InfoCmp.Capability.clear_screen);
        StringBuilder s = new StringBuilder("===>");
        StringBuilder _game_frame = new StringBuilder();
        loading(s);

        keyBoardInput = new KeyBoardInput(display);
        obstacles = new Obstacles(width, height);
        ob = obstacles.getObstacles_list();
        var _e1 = _getEntity(b1);
        var _e2 = _getEntity(b2);
        ani=new AnimateAsciiImage(_e1.s(),_e2.s());
        ani.animate();
        bird=_e1.list().toArray(new BirdParts[]{});
        while (keyBoardInput.getKeyBoardKey() != Key.ESC) {
            boolean condition = !isBirdDead();
            _game_frame.append(message("", width / 2, Position.Center, "Flappy Bird")).append("\n");
            _game_frame = getStringBuilder(_game_frame, condition);
            Thread.sleep(110);
            animate_bird();
            display.terminal.puts(InfoCmp.Capability.clear_screen);
            //cls();
        }
        ani.stopAnimation();
        System.exit(-1);
    }

    private void animate_bird() {
        var list = ani.getF1().split("\n");
        int j=0;
        for (int i = 0; i < bird.length;) {
            var str=list[j];
            for (int k = 0; k <str.length() ; k++) {
                bird[i].text=""+str.charAt(k);
                i++;
            }
            j++;
        }
    }

    private StringBuilder getStringBuilder(StringBuilder s, boolean condition) {
        if (condition) {
            check_for_score();
            memory=new HashSet<>();
            move(obstacles);
            draw(s);
            s.append(message("Score : " + score, width + 1, Position.Right, "Level : " + level));
            System.out.println(s);
            s = new StringBuilder();
        } else {
            ani.stopAnimation();
            draw(s);
            s.append(message("Score : " + score, width + 1, Position.Right, "Level : " + level)).append("\n");
            s.append("Oh! I got stuck by an obstacle. Best luck next time :)").append("\n");
            s.append("Enter the space bar to continue or press ESC to exit.");
            System.out.println(s);
            s = new StringBuilder();
            s = reset(s);
        }
        return s;
    }

    private StringBuilder reset(StringBuilder s) {
        if (keyBoardInput.getKeyBoardKey() == Key.SPACE) {
            memory = new HashSet<>();
            condition = false;
            bird = _getEntity(b1).list().toArray(new BirdParts[]{});
            ani.animate();
            score = 0;
            s = new StringBuilder();
            obstacles = new Obstacles(width, height);
            ob = obstacles.getObstacles_list();
        }
        return s;
    }

    private void check_for_score() {
        if (!memory.isEmpty()) {
            condition = true;
        } else if (condition) {
            score++;
            if (score % 10 == 0) {
                level++;

            }

            condition = false;
        }
    }

    private void draw(StringBuilder s) {
        for (int i = 0; i <= height; i++) {
            for (int j = 0; j <= width; j++) {
                boolean b = isValidPoint(j, i);
                var is_part = isBirdPart(j, i);
                if (isSidePoint(j, i))
                    s.append("\033[0;97m█\33[0m");
                else if (is_part.condition())
                    s.append(is_part.x());
                else if (b)
                    s.append(ob.getFirst().edge1().getBlock());
                else
                    s.append(" ");

            }
            s.append('\n');
        }
    }

    private String message(String startWith, int length, Position position, String endWith) {
        return switch (position) {
            case Left, Right -> startWith + (" ".repeat(length - startWith.length() - endWith.length())) + endWith;
            case Center -> startWith + (" ".repeat(length - startWith.length() - endWith.length() / 2)) + endWith;
        };
    }

    private IsPart isBirdPart(int j, int i) {
        for (var a : bird) {
            if (a.point.y == i && a.point.x == j)
                return new IsPart(true, a.text);
        }
        return new IsPart(false, "");
    }

    private boolean isSidePoint(int j, int i) {
        return i == 0 || j == 0 || i == height || j == width;
    }

    private void move(Obstacles ob) {
        var dir = keyBoardInput.getKeyBoardKey();
        var k = ob.getObstacles_list();
        if (dir == Key.UP) {
            for (var br : bird) {
                br.point.y = br.point.y - 3;
            }
        } else {
                for (var br : bird) {
                    br.point.y++;
                }
        }

        for (var r : k) {
            r.edge1().x = r.edge1().x - speed;
            r.edge2().x = r.edge2().x - speed;
        }
        if (k.getFirst().edge1().x < 0 && k.getFirst().edge2().x < 0) {
            k.poll();
            ob.addObstacle();
        }
        keyBoardInput.setKeyBoardKey(Key.NONE);
    }
    private boolean isValidPoint(int j, int i) {
        for (var r : ob) {
            if ((j >= r.edge1().x && j <= r.edge2().x) && (i >= r.edge1().y && i <= r.edge3().y)) {
                if (j >= width / 3 && j <= width / 3 + MAX_LENGTH - 2) {
                    memory.add(i);
                }
                return true;
            }
        }
        return false;
    }

    private boolean isBirdDead() {
        for (var b : bird) {
            if (memory.contains(b.point.y))
                return true;
            else if (b.point.y == height)
                return true;
            else if (b.point.y == 0)
                return true;
        }

        return false;
    }
    record Tuple(String s,ArrayList<BirdParts> list){

    }
    private void loading(StringBuilder s) throws InterruptedException {
        while (s.length() <= 65) {
            System.out.println(TITLE.indent(25));
            s.insert(0, "=");
            System.out.println(s.toString().indent(25));
            Thread.sleep(10);
            display.terminal.puts(InfoCmp.Capability.clear_screen);
        }
    }
}
