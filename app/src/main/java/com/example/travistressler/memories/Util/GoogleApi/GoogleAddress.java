package com.example.travistressler.memories.Util.GoogleApi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by travistressler on 10/25/17.
 */

public class GoogleAddress {
    @SerializedName("results") private List<Results> results;

    public List<Results> getResults() {
        return results;
    }

    public class Results {

        @SerializedName("formatted_address")
        String addressName;

        @SerializedName("geometry") private Geometry geometry;

        public Geometry getGeometry() {
            return geometry;
        }

        public String getAddressName() {
            return addressName;
        }
    }

    public class Geometry {
        @SerializedName("location") private GoogleLocation location;

        public GoogleLocation getLocation() {
            return location;
        }
    }

    public class GoogleLocation {
        @SerializedName("lat") private double latitude;

        @SerializedName("lng") private double longitude;

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }
    }
}
