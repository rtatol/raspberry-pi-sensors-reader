package com.rpi.sensors.reader.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Builder
@Getter
@ToString
public class Gauges {

    private final String deviceId;

    private final Map<String, Double> gauges;
}
