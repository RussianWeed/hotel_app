package com.devking.hotel_app_andoird;

import java.util.Date;

public class Explore_request_post {

    String location,checkin,checkout;

    public Explore_request_post(String checkin, String checkout, String location) {
        this.checkin = checkin;
        this.checkout = checkout;
        this.location = location;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
