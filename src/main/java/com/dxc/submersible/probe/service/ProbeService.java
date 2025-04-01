package com.dxc.submersible.probe.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProbeService {
    private static final Logger logger = LoggerFactory.getLogger(ProbeService.class);
    private int x, y;
    private String direction;
    private final Set<String> visited = new LinkedHashSet<>();
    private final Set<String> obstacles = new HashSet<>();
    private static final int GRID_SIZE = 10;
    private static final Map<String, int[]> MOVEMENTS = Map.of(
            "N", new int[]{0, 1}, "S", new int[]{0, -1}, "E", new int[]{1, 0}, "W", new int[]{-1, 0});
    private static final List<String> DIRECTIONS = List.of("N", "E", "S", "W");

    public void initialize(int x, int y, String direction, List<int[]> obstacleList) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        visited.clear();
        visited.add(x + "," + y);
        obstacles.clear();
        obstacleList.forEach(ob -> obstacles.add(ob[0] + "," + ob[1]));
        logger.info("Probe initialized at ({}, {}) Facing {}", x, y, direction);
    }

    public String processCommands(String commands) {
        for (char command : commands.toCharArray()) {
            if (command == 'L' || command == 'R') {
                rotate(command);
            } else {
                move(command);
            }
        }

        if(direction!=null)
            logger.info("Final Position: ({}, {}) facing {}", x, y, direction);
        else
            logger.info("Probe is not initialized");
        String result = direction!=null ? "Final Position: (" + x + ", " + y + ") facing " + direction + " | Visited: " + visited : "Probe is not initialized.";

        return result;
    }

    private void rotate(char command) {
        if(direction!=null) {
            int index = DIRECTIONS.indexOf(direction);
            direction = (command == 'L') ? DIRECTIONS.get((index + 3) % 4) : DIRECTIONS.get((index + 1) % 4);
            logger.info("Rotated {} to face {} Direction", command == 'L' ? "Left" : "Right", direction);
        }
    }

    private void move(char command) {
        if(direction!=null){
            int dx = MOVEMENTS.get(direction)[0];
            int dy = MOVEMENTS.get(direction)[1];
            if (command == 'B') {
                dx = -dx;
                dy = -dy;
            }
            int newX = x + dx, newY = y + dy;
            if (newX >= 0 && newX < GRID_SIZE && newY >= 0 && newY < GRID_SIZE && !obstacles.contains(newX + "," + newY)) {
                x = newX;
                y = newY;
                visited.add(x + "," + y);
                logger.info("Command {} Moved to ({}, {})", command, x, y);
            } else {
                logger.warn("Movement Blocked at co-ordinates ({}, {}) due to obstacle or grid boundary", newX, newY);
            }
        }
    }
}
