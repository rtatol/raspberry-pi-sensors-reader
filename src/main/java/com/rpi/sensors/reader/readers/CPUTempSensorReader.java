package com.rpi.sensors.reader.readers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CPUTempSensorReader implements SensorReader {

    private final static String CPU_TEMP_FILE = "/sys/class/thermal/thermal_zone0/temp";
    private final static String GAUGE_NAME = "cpu_temperature";

    @Override
    public Map<String, Double> read() {
        final Map<String, Double> values = new HashMap<>(1);

        try {
            final String rawValue = new String(Files.readAllBytes(Paths.get(CPU_TEMP_FILE)));
            final Double value = Double.valueOf(rawValue) / 1000.0;
            values.put(GAUGE_NAME, value);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return values;
    }
}
