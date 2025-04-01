package com.dxc.submersible.probe;

import com.dxc.submersible.probe.service.ProbeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProbeApplicationTests {

    @Autowired
    ProbeService probeService;

    @BeforeEach
    void setup() {
        probeService.initialize(0, 0, "N", List.of(new int[]{2, 2}));
    }

    @Test
    void testMoveForward() {
        String result = probeService.processCommands("F");
        Assertions.assertTrue(result.contains("Final Position: (0, 1)"));
    }

    @Test
    void testMoveBackward() {
        String result = probeService.processCommands("B");
        Assertions.assertTrue(result.contains("Final Position: (0, 0)"));
    }

    @Test
    void testRotateLeft() {
        String result = probeService.processCommands("L");
        Assertions.assertTrue(result.contains("facing W"));
    }

    @Test
    void testRotateRight() {
        String result = probeService.processCommands("R");
        Assertions.assertTrue(result.contains("facing E"));
    }

    @Test
    void testObstacleAvoidance() {
        probeService.initialize(1, 1, "N", List.of(new int[]{1, 2}));
        String result = probeService.processCommands("F");
        Assertions.assertTrue(result.contains("Final Position: (1, 1)")); // Stays in place
    }
}
