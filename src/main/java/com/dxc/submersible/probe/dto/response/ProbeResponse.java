package com.dxc.submersible.probe.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProbeResponse {
    private Long id;
    private String name;
    private String position;
    private String status;
    private String message;

}
