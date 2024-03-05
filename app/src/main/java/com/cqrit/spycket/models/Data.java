package com.cqrit.spycket.models;

import androidx.annotation.NonNull;

public class Data {
    private final String ip_src;
    private final String ip_dst;
    private final String mac_src;
    private final String mac_dst;




    // Constructor
    public Data(String ip_src, String ip_dst, String mac_src, String mac_dst) {
        this.ip_src = ip_src;
        this.ip_dst = ip_dst;
        this.mac_src = mac_src;
        this.mac_dst = mac_dst;
    }

    public String getIp_src() {
        return ip_src;
    }

    public String getIp_dst() {
        return ip_dst;
    }

    public String getMac_src() {
        return mac_src;
    }

    public String getMac_dst() {
        return mac_dst;
    }

    // Getters
    /*@NonNull
    public String toString() {
        return this.ip_src;
    }*/
}