package com.oandmdigital.mappingapp.model;

import java.util.List;

public class Shop {

    private String mName;
    private Address mAddress;
    private List<OpeningTime> mOpeningTimes;
    private String mUrl;
    private String mTelephone;
    private String mImageUrl;
    private double mReview;
    private double mLongitude;
    private double mLatitude;


    public Shop(String name,
                Address address,
                List<OpeningTime> openingTimes,
                String url,
                String telephone,
                String imageUrl,
                double review,
                double longitude,
                double latitude) {

        mName = name;
        mAddress = address;
        mOpeningTimes = openingTimes;
        mUrl = url;
        mTelephone = telephone;
        mImageUrl = imageUrl;
        mReview = review;
        mLongitude = longitude;
        mLatitude = latitude;
    }


    public String getName() {
        return mName;
    }

    public Address getAddress() {
        return mAddress;
    }

    public List<OpeningTime> getOpeningTimes() {
        return mOpeningTimes;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getTelephone() {
        return mTelephone;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public double getReview() {
        return mReview;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public double getLatitude() {
        return mLatitude;
    }


}
