package com.mogikanlol.game.core.entity;

import com.mogikanlol.game.core.contants.GameConstants;
import com.mogikanlol.game.core.util.MVector2;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Snake {

    private static final Map<SnakeDirection, MVector2> DIRECTION_TO_VECTOR;
    private MVector2 vectorDir;
    private SnakeDirection currentDirection;
    private List<SnakeBlock> body;

    static {
        DIRECTION_TO_VECTOR = new EnumMap<>(SnakeDirection.class);
        DIRECTION_TO_VECTOR.put(SnakeDirection.UP, new MVector2(0, GameConstants.BLOCK_SIZE));
        DIRECTION_TO_VECTOR.put(SnakeDirection.DOWN, new MVector2(0, -GameConstants.BLOCK_SIZE));
        DIRECTION_TO_VECTOR.put(SnakeDirection.LEFT, new MVector2(-GameConstants.BLOCK_SIZE, 0));
        DIRECTION_TO_VECTOR.put(SnakeDirection.RIGHT, new MVector2(GameConstants.BLOCK_SIZE, 0));
    }

    public Snake() {
        body = new ArrayList<>();
    }

    public void reset() {
        List<SnakeBlock> body = getBody();
        int blockSize = GameConstants.BLOCK_SIZE;

        body.clear();

        int startX = blockSize * 5;
        int startY = blockSize * 5;

        SnakeBlock head = new SnakeBlock();
        head.setSize(blockSize - 1, blockSize - 1);
        head.setPosition(startX, startY);

        SnakeBlock bone = new SnakeBlock();
        bone.setSize(blockSize - 1, blockSize - 1);
        bone.setPosition(startX - blockSize, startY);

        SnakeBlock tail = new SnakeBlock();
        tail.setSize(blockSize - 1, blockSize - 1);
        tail.setPosition(startX - blockSize * 2, startY);

        setVectorDir(new MVector2(blockSize, 0));
        setCurrentDirection(SnakeDirection.RIGHT);

        body.add(head);
        body.add(bone);
        body.add(tail);
    }

    public void updateSnake() {
        int blockSize = GameConstants.BLOCK_SIZE;

        List<SnakeBlock> body = getBody();
        MVector2 vectorDir = getVectorDir();

        if (body.size() > 1) {
            SnakeBlock snakeBlock = new SnakeBlock();
            snakeBlock.setSize(blockSize - 1, blockSize - 1);
            snakeBlock.setPosition(body.get(0).getX(), body.get(0).getY());

            body.add(0, snakeBlock);
            body.remove(body.size() - 1);
        }

        SnakeBlock head = body.get(0);

        int updatedX = head.getX() + vectorDir.getX();
        int updatedY = head.getY() + vectorDir.getY();

        head.setX(updatedX);
        head.setY(updatedY);

    }

    public void grow() {
        int blockSize = GameConstants.BLOCK_SIZE;
        List<SnakeBlock> body = getBody();
        MVector2 vectorDir = getVectorDir();

        SnakeBlock last = body.get(body.size() - 1);

        SnakeBlock snakeBlock = new SnakeBlock();
        snakeBlock.setSize(blockSize - 1, blockSize - 1);
        snakeBlock.setPosition(last.getX() - vectorDir.getX(), last.getY() - vectorDir.getY());

        body.add(snakeBlock);
    }

    public void setDirection(SnakeDirection direction) {
        SnakeDirection currentDirection = getCurrentDirection();

        if (direction == SnakeDirection.UP && currentDirection != SnakeDirection.DOWN) {
            setVectorDir(DIRECTION_TO_VECTOR.get(direction));
            setCurrentDirection(SnakeDirection.UP);
        }
        else if (direction == SnakeDirection.DOWN && currentDirection != SnakeDirection.UP) {
            setVectorDir(DIRECTION_TO_VECTOR.get(direction));
            setCurrentDirection(SnakeDirection.DOWN);
        }
        else if (direction == SnakeDirection.LEFT && currentDirection != SnakeDirection.RIGHT) {
            setVectorDir(DIRECTION_TO_VECTOR.get(direction));
            setCurrentDirection(SnakeDirection.LEFT);
        }
        else if (direction == SnakeDirection.RIGHT && currentDirection != SnakeDirection.LEFT) {
            setVectorDir(DIRECTION_TO_VECTOR.get(direction));
            setCurrentDirection(SnakeDirection.RIGHT);
        }
    }

    public MVector2 getVectorDir() {
        return vectorDir;
    }

    public List<SnakeBlock> getBody() {
        return body;
    }

    public SnakeBlock getHead() {
        return body.get(0);
    }

    public SnakeDirection getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(SnakeDirection currentDirection) {
        this.currentDirection = currentDirection;
    }

    public void setVectorDir(MVector2 vectorDir) {
        this.vectorDir = vectorDir;
    }

}
