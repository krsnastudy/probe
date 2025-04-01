package com.dxc.submersible.probe.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class ProbeRequest {
    private int x, y;
    private String direction;
    private List<int[]> obstacles;
}