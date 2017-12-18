package com.rpi.sensors.reader.readers;

import java.util.Map;

public interface SensorReader {

    public Map<String, Double> read();
}
