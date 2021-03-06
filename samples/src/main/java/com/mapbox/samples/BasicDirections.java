package com.mapbox.samples;

import com.mapbox.directions.v5.DirectionsCriteria;
import com.mapbox.directions.v5.MapboxDirections;
import com.mapbox.directions.v5.models.DirectionsResponse;
import com.mapbox.geojson.Point;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Shows how to make a request using the minimum required params.
 */
public class BasicDirections {
  public static void main(String[] args) {

    // 1. Build the directions request using the provided builder.
    MapboxDirections directions = buildMapboxDirections();

    directions.enqueueCall(new Callback<DirectionsResponse>() {
      @Override
      public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
        System.out.println(response.code());
        System.out.println(call.request().url());
        System.out.println(response.body().routes().get(0).legs().get(0).steps().get(0).maneuver().location().latitude());
        System.out.println(response.body().routes().get(0).distance());
        System.out.println(response.body().routes().get(0).routeOptions().profile());
        System.out.println(response.body().routes().get(0).routeOptions().alternatives());
        System.out.println(response.body().routes().get(0).routeOptions().user());
        System.out.println(response.body().routes().get(0).legs().get(0).steps().get(0).maneuver().toString());
        System.out.println(response.body().routes().get(0).legs().get(0).steps().get(0)
          .voiceInstructions().get(0).announcement());
        System.out.println(response.body().routes().get(0).legs().get(0).annotation().congestion().size());
        System.out.println("Distance: " + response.body().routes().get(0).legs().get(0).steps().get(0).bannerInstructions().get(0).distanceAlongGeometry());
      }

      @Override
      public void onFailure(Call<DirectionsResponse> call, Throwable throwable) {
        System.out.println(call.request().url());
        System.out.println(throwable);
      }
    });
  }

  private static MapboxDirections buildMapboxDirections() {
    return MapboxDirections.builder()
      .accessToken("pk.eyJ1IjoiY2FtbWFjZSIsImEiOiI5OGQxZjRmZGQ2YjU3Mzk1YjJmZTQ5ZDY2MTg1NDJiOCJ9.hIFoCKGAGOwQkKyVPvrxvQ")
      .origin(Point.fromLngLat(-95.6332, 29.7890))
      .destination(Point.fromLngLat(-95.3591, 29.7576))
      .bannerInstructions(true)
      .voiceInstructions(true)
      .annotations(DirectionsCriteria.ANNOTATION_CONGESTION)
      .overview(DirectionsCriteria.OVERVIEW_FULL)
      .addBearing(null, null)
      .radiuses(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)
      .steps(true)
//      .voiceUnits(DirectionsCriteria.METRIC)
      .build();
  }
}
