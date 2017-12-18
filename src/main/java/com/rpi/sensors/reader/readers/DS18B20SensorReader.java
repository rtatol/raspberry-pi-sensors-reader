package com.rpi.sensors.reader.readers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DS18B20SensorReader implements SensorReader {

    private final static String DS18B20_FILE = "/sys/bus/w1/devices/28-000006250f36/w1_slave";
    private final static String GAUGE_NAME = "temperature";

    private final Pattern pattern = Pattern.compile("t=(\\d+)");

    @Override
    public Map<String, Double> read() {
        final Map<String, Double> values = new HashMap<>(1);

        try {
            final String rawValue = new String(Files.readAllBytes(Paths.get(DS18B20_FILE)));
            final Matcher matcher = pattern.matcher(rawValue);

            if (matcher.find()) {
                final Double value = Double.valueOf(matcher.group(1)) / 1000.0;
                values.put(GAUGE_NAME, value);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return values;
    }
}
