package com.cqrit.spycket.models;

import androidx.annotation.NonNull;

public class CaptureData {
    private final String date;
    private final String description_interface;
    private final String dst_port;
    private final int id;
    private final String ip_dst;
    private final String ip_src;
    private final String mac;
    private final String mac_dst;
    private final String mac_src;
    private final String protocol_application;
    private final String protocol_transport;
    private final String src_port;
    private final int tcp_ack;
    private final int numero_trame;
    private final String ip;

    // Constructor
    public CaptureData(String date, String descriptionInterface, String dstPort, String ipDst, String mac, String macSrc, int numero_trame, int id, String ipSrc, String macDst, String protocolApplication, String protocolTransport, String srcPort, int tcpAck, String ip) {
        this.date = date;
        this.description_interface = descriptionInterface;
        this.dst_port = dstPort;
        this.ip_dst = ipDst;
        this.mac = mac;
        this.mac_src = macSrc;
        //TODO  : CHANGE NAME OF VARIABLES
        this.numero_trame = numero_trame;
        this.id = id;
        this.ip_src = ipSrc;
        this.mac_dst = macDst;
        this.protocol_application = protocolApplication;
        this.protocol_transport = protocolTransport;
        this.src_port = srcPort;
        this.tcp_ack = tcpAck;
        this.ip = ip;
    }

    // Getters
    public int getUserId() {
        return numero_trame;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return ip;
    }

    public String getProtocol_application() {
        return protocol_application;
    }

    public String getProtocol_transport() {
        return protocol_transport;
    }

    public String getSrc_port() {
        return src_port;
    }

    public int getTcp_ack() {
        return tcp_ack;
    }

    public String getDescription_interface() {
        return description_interface;
    }

    public String getDst_port() {
        return dst_port;
    }

    public String getIp_dst() {
        return ip_dst;
    }

    public String getIp_src() {
        return ip_src;
    }

    public String getMac() {
        return mac;
    }

    public String getMac_dst() {
        return mac_dst;
    }

    public String getMac_src() {
        return mac_src;
    }

    @NonNull
    public String toString() {
        return this.date;
    }
}