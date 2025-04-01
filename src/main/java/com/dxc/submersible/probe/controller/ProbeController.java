package com.dxc.submersible.probe.controller;

import com.dxc.submersible.probe.dto.request.MoveProbeRequest;
import com.dxc.submersible.probe.dto.request.ProbeRequest;
import com.dxc.submersible.probe.dto.response.ProbeResponse;
import com.dxc.submersible.probe.service.ProbeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/probe")
public class ProbeController {

    @Autowired
    private ProbeService probeService;

    /**
     * Initialising the Probe movement with Direction and co-ordinates
     * */
    @PostMapping("/start")
    public ResponseEntity<ProbeResponse> initializeProbe(@RequestBody ProbeRequest request) {
        ProbeResponse probeResponse = new ProbeResponse();
        ResponseEntity<ProbeResponse> oEntity = null;
        String result = "";

        probeService.initialize(request.getX(), request.getY(), request.getDirection(), request.getObstacles());
        result = "Probe initialized at (" + request.getX() + ", " + request.getY() + ") facing " + request.getDirection();
        probeResponse.setMessage(result);

        oEntity = new ResponseEntity<ProbeResponse>(probeResponse, HttpStatus.OK);
        return oEntity;
    }

    /**
     * Moving the Probe with commands like F-Forward, B-Backward
     * R-Rotate Right, L-Rotate Left
     * */
    @PostMapping("/move")
    public ResponseEntity<ProbeResponse> moveProbe(@RequestBody MoveProbeRequest request) {
        ProbeResponse probeResponse = new ProbeResponse();
        ResponseEntity<ProbeResponse> oEntity = null;

        String result = probeService.processCommands(request.getCommands());
        probeResponse.setMessage(result);

        oEntity = new ResponseEntity<ProbeResponse>(probeResponse, HttpStatus.OK);
        return oEntity;
    }
}
