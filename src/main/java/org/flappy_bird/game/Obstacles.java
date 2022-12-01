package org.flappy_bird.game;

import java.util.ArrayDeque;


public class Obstacles {
    private final int height;
    private final int thickness = 8;
    private final int k=15;
    private  int difference;
    private final int width;
    public ArrayDeque<Block> getObstacles_list() {
        return obstacles_list;
    }
    public Obstacles(int width,int height) {
        this.width=width;
        this.height = height;
        createObstacles(height);
    }

    private final ArrayDeque<Block> obstacles_list = new ArrayDeque<>();

    private void createObstacles(int height) {
        int startX = width-10;
        int depth;
        depth = height / getStupidConstant(3) + (int) (Math.random() * height / 4+1);
        obstacles_list.add(new Block(new Point(startX, 0)
                , new Point(startX + thickness, 0),
                new Point(startX, depth),
                new Point(startX + thickness, depth)));
        depth = depth + getStupidConstant(12);
        obstacles_list.add(new Block(new Point(startX, depth)
                , new Point(startX + thickness, depth),
                new Point(startX, height),
                new Point(startX + thickness, height)));
        for (int i = 1; i <= 9; i++) {
            difference=/*(int)(Math.random()*k+1)*/k;
            var r0 = obstacles_list.getLast();
            var edge2 = r0.edge2();
            depth = height / getStupidConstant(3) + (int) (Math.random() * height / 4+1);
            obstacles_list.add(new Block(new Point(edge2.x + thickness+ difference, 0)
                    , new Point(edge2.x + 2 * thickness+ difference, 0),
                    new Point(edge2.x + thickness+ difference, depth),
                    new Point(edge2.x + 2 * thickness+ difference, depth)));
            depth = depth + getStupidConstant(12);
            obstacles_list.add(new Block(new Point(edge2.x + thickness+ difference, depth)
                    , new Point(edge2.x + thickness * 2+ difference, depth),
                    new Point(edge2.x + thickness+ difference, height),
                    new Point(edge2.x + thickness * 2+ difference, height)));

        }
    }

    public void addObstacle() {
        var r0 = obstacles_list.getLast();
        var edge2 = r0.edge2();
        int depth;
        depth = height / getStupidConstant(3) + (int) (Math.random() * height / 4+1);
        difference=/*(int)(Math.random()*k+1)*/k;
        obstacles_list.add(new Block(new Point(edge2.x + thickness+ difference, 0)
                , new Point(edge2.x + 2 * thickness+ difference, 0),
                new Point(edge2.x + thickness+ difference, depth),
                new Point(edge2.x + 2 * thickness+ difference, depth)));
        depth = depth + getStupidConstant(12);
        obstacles_list.add(new Block(new Point(edge2.x + thickness+ difference, depth)
                , new Point(edge2.x + thickness * 2+ difference, depth),
                new Point(edge2.x + thickness+ difference, height),
                new Point(edge2.x + thickness * 2+ difference, height)));
    }

    private int getStupidConstant(int constant){
        return constant+ (int) (Math.random() * height / constant+1);
    }

}

record Block(Point edge1, Point edge2, Point edge3, Point edge4) {

}

