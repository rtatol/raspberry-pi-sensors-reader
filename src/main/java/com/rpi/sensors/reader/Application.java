package com.rpi.sensors.reader;

import com.rpi.sensors.reader.dto.Gauges;
import com.rpi.sensors.reader.readers.CPUTempSensorReader;
import com.rpi.sensors.reader.readers.DS18B20SensorReader;
import com.rpi.sensors.reader.readers.SensorReader;
import com.rpi.sensors.reader.service.SensorsServerClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Application {

    private final List<SensorReader> readers;
    private final SensorsServerClient sensorsServerClient;

    private Application() {
        this.readers = new ArrayList<>(2);
        this.readers.add(new DS18B20SensorReader());
        this.readers.add(new CPUTempSensorReader());
        this.sensorsServerClient = new SensorsServerClient();
    }

    private void readAll() throws Exception {

        final Gauges gauges = Gauges.builder()
                .deviceId("rpi")
                .gauges(new HashMap<>(readers.size()))
                .build();

        readers.forEach(reader -> gauges.getGauges().putAll(reader.read()));

        sensorsServerClient.send(gauges);
    }

    public static void main(final String... args) throws Exception {
        new Application().readAll();
    }
}
