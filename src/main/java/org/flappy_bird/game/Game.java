package org.flappy_bird.game;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashSet;

import static org.flappy_bird.game.Main.cls;


public class Game {
    private final int width;
    private final int height;
    private Key bird_feather = Key.DOWN;
    int score = 0;
    int level = 0;
    int bird_frame =0;
    boolean condition = false;
    int speed = 1;
    private Obstacles obstacles;
    private ArrayDeque<Block> ob ;
    private KeyBoardInput keyBoardInput;
    private HashSet<Integer> memory = new HashSet<>();
    private BirdParts[] bird;

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
    }

    void start() throws InterruptedException, IOException {
        StringBuilder s = new StringBuilder();
        keyBoardInput = new KeyBoardInput();
        obstacles = new Obstacles(width,height);
        ob = obstacles.getObstacles_list();
        bird=new BirdParts[]{new BirdParts("O", new Point(width/3, height / 2)),
                new BirdParts("/", new Point(width/3-1, height / 2 + 1)),
                new BirdParts("\\", new Point(width/3+1, height / 2 + 1)),
                new BirdParts("/", new Point(width/3-2, height / 2 + 2)),
                new BirdParts("\\", new Point(width/3+2, height / 2 + 2)),
        };
        while (keyBoardInput.getKeyBoardKey() != Key.ESC) {
            boolean condition = !isBirdDead();
            System.out.println(message("", width / 2, Pos.Center, "Flappy Bird") + "\n");
            if (condition) {
                s = new StringBuilder();
                check_for_score();
                memory.clear();
                move(obstacles);
                draw(s);
                System.out.println(s);
                System.out.println(message("Score : " + score, width + 1, Pos.Right, "Level : " + level));
            } else {
                System.out.println(s);
                System.out.println(message("Score : " + score, width + 1, Pos.Right, "Level : " + level));
                System.out.println("Oh! I got stuck by an obstacle. Best luck next time :)");
                System.out.println("Enter the space bar to continue or press ESC to exit.");
                s = reset(s);
            }
            Thread.sleep(50);
            cls();
        }
        System.exit(-1);
    }

    private StringBuilder reset(StringBuilder s) {
        if (keyBoardInput.getKeyBoardKey() == Key.SPACE) {
            memory = new HashSet<>();
            condition = false;
            bird = new BirdParts[]{new BirdParts("O", new Point(width/3, height / 2)),
                    new BirdParts("/", new Point(width/3-1, height / 2 + 1)),
                    new BirdParts("\\", new Point(width/3+1, height / 2 + 1)),
                    new BirdParts("/", new Point(width/3-2, height / 2 + 2)),
                    new BirdParts("\\", new Point(width/3+2, height / 2 + 2)),
            };
            score = 0;
            s = new StringBuilder();
            obstacles = new Obstacles(width,height);
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
                if (level % 10 == 0)
                    speed++;

            }

            condition = false;
        }
    }

    private void draw(StringBuilder s) {
        for (int i = 0; i <= height; i++) {
            for (int j = 0; j <= width; j++) {
                boolean b = isvalidPoint(j, i);
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

    private String message(String startWith, int length, Pos pos, String endWith) {
        return switch (pos) {
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

    private void move(Obstacles ob) throws InterruptedException {
        var dir = keyBoardInput.getKeyBoardKey();
        var k = ob.getObstacles_list();
        if (dir == Key.UP) {
            if (bird_feather == Key.UP) {
                move_feather(1);
                bird_feather = Key.DOWN;
            } else {
                move_feather(-1);
                bird_feather = Key.UP;
            }
            for (var br : bird) {
                br.point.y--;
            }
        } else {
            if (bird_frame ==1){
            for (var br : bird) {
                br.point.y++;
            }
            bird_frame =0;
            }
            else bird_frame++;
            move_feather(1);
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

    private void move_feather(int i) {
        if (i < 0) {
            bird[1].text = "\\";
            bird[2].text = "/";
            bird[3].text = "\\";
            bird[4].text = "/";
        } else {
            bird[1].text = "/";
            bird[2].text = "\\";
            bird[3].text = "/";
            bird[4].text = "\\";
        }
        bird[1].point.y = bird[0].point.y + (i);
        bird[2].point.y = bird[0].point.y + (i);
        bird[3].point.y = bird[0].point.y + (2 * i);
        bird[4].point.y = bird[0].point.y + (2 * i);
    }

    private boolean isvalidPoint(int j, int i) {
        for (var r : ob) {
            if ((j >= r.edge1().x && j <= r.edge2().x) && (i >= r.edge1().y && i <= r.edge3().y)) {
                if (j >= width/3-2 && j <=  width/3+2) {
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
}
