package com.mapbox.services.staticimage.v1;

import com.mapbox.services.Constants;
import com.mapbox.services.commons.ServicesException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import okhttp3.HttpUrl;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertFalse;

public class MapboxStaticImageTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testSanity() throws ServicesException {
        MapboxStaticImage client = new MapboxStaticImage.Builder()
                .setAccessToken("pk.")
                .setStyleId(Constants.MAPBOX_STYLE_STREETS)
                .setLat(1.0).setLon(2.0)
                .setZoom(10)
                .setWidth(100).setHeight(200)
                .build();
        HttpUrl url = client.getUrl();
        assertFalse(url.toString().isEmpty());
    }

    @Test
    public void requireAccessToken() throws ServicesException {
        thrown.expect(ServicesException.class);
        thrown.expectMessage(startsWith("Using Mapbox Services requires setting a valid access token."));
        new MapboxStaticImage.Builder()
                .setStyleId(Constants.MAPBOX_STYLE_STREETS)
                .setLat(1.0).setLon(2.0)
                .setZoom(10)
                .setWidth(100).setHeight(200)
                .build();
    }

    @Test
    public void requireStyleId() throws ServicesException {
        thrown.expect(ServicesException.class);
        thrown.expectMessage(startsWith("You need to set a map style."));
        new MapboxStaticImage.Builder()
                .setAccessToken("pk.")
                .setLat(1.0).setLon(2.0)
                .setZoom(10)
                .setWidth(100).setHeight(200)
                .build();
    }

    @Test
    public void requireLon() throws ServicesException {
        thrown.expect(ServicesException.class);
        thrown.expectMessage(startsWith("You need to set the map lon/lat coordinates."));
        new MapboxStaticImage.Builder()
                .setAccessToken("pk.")
                .setStyleId(Constants.MAPBOX_STYLE_STREETS)
                .setLat(1.0)
                .setZoom(10)
                .setWidth(100).setHeight(200)
                .build();
    }

    @Test
    public void requireLat() throws ServicesException {
        thrown.expect(ServicesException.class);
        thrown.expectMessage(startsWith("You need to set the map lon/lat coordinates."));
        new MapboxStaticImage.Builder()
                .setAccessToken("pk.")
                .setStyleId(Constants.MAPBOX_STYLE_STREETS)
                .setLon(2.0)
                .setZoom(10)
                .setWidth(100).setHeight(200)
                .build();
    }

    @Test
    public void requireZoom() throws ServicesException {
        thrown.expect(ServicesException.class);
        thrown.expectMessage(startsWith("You need to set the map zoom level."));
        new MapboxStaticImage.Builder()
                .setAccessToken("pk.")
                .setStyleId(Constants.MAPBOX_STYLE_STREETS)
                .setLat(1.0).setLon(2.0)
                .setWidth(100).setHeight(200)
                .build();
    }

    @Test
    public void requireWidth() throws ServicesException {
        thrown.expect(ServicesException.class);
        thrown.expectMessage(startsWith("You need to set the map width/height dimensions."));
        new MapboxStaticImage.Builder()
                .setAccessToken("pk.")
                .setStyleId(Constants.MAPBOX_STYLE_STREETS)
                .setLat(1.0).setLon(2.0)
                .setZoom(10)
                .setHeight(200)
                .build();
    }

    @Test
    public void requireHeight() throws ServicesException {
        thrown.expect(ServicesException.class);
        thrown.expectMessage(startsWith("You need to set the map width/height dimensions."));
        new MapboxStaticImage.Builder()
                .setAccessToken("pk.")
                .setStyleId(Constants.MAPBOX_STYLE_STREETS)
                .setLat(1.0).setLon(2.0)
                .setZoom(10)
                .setWidth(100)
                .build();
    }

}
