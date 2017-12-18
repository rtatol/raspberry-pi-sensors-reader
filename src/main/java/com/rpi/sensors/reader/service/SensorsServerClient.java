package com.rpi.sensors.reader.service;

import com.rpi.sensors.reader.dto.Gauges;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.io.IOException;

public class SensorsServerClient {

    private final SensorsService service;

    public SensorsServerClient() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://sensors-server:1235")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        this.service = retrofit.create(SensorsService.class);
    }

    public Response<ResponseBody> send(final Gauges gauges) throws IOException {
        System.out.println(String.format("Sending request %s", gauges.toString()));
        final Response<ResponseBody> response = service.send(gauges).execute();
        System.out.println(response.toString());
        return response;
    }

    static interface SensorsService {

        @POST("/")
        Call<ResponseBody> send(@Body Gauges gauges);
    }
}
