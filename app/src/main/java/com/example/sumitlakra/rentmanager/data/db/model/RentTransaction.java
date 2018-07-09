package com.example.sumitlakra.rentmanager.data.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "RENTTRANSACTION")
public class RentTransaction {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "roomNo")
    private String roomNo;

    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "totalRent")
    private int totalRent =0;

    @Property(nameInDb = "rentPaid")
    private int rentPaid =0;

    @Property(nameInDb = "balance")
    private int balance =1;

    @Property(nameInDb = "timeStamp")
    private String timeStamp;

    @Property(nameInDb = "month")
    private String month;

    @Property(nameInDb = "rentStatus")
    private boolean rentStatus = false;

    @Property(nameInDb = "comments")
    private String comments;

    @Property(nameInDb = "electricityBill")
    private int electricityBill;

    @Property(nameInDb = "waterBill")
    private int waterBill;

    public RentTransaction(String roomNo, String name, int totalRent,
                           int rentPaid, int balance, String timeStamp, String month,
                           boolean rentStatus, String comments) {
        this.roomNo = roomNo;
        this.name = name;
        this.totalRent = totalRent;
        this.rentPaid = rentPaid;
        this.balance = balance;
        this.timeStamp = timeStamp;
        this.month = month;
        this.rentStatus = rentStatus;
        this.comments = comments;
    }

    @Generated(hash = 1545041936)
    public RentTransaction(Long id, String roomNo, String name, int totalRent, int rentPaid,
            int balance, String timeStamp, String month, boolean rentStatus, String comments,
            int electricityBill, int waterBill) {
        this.id = id;
        this.roomNo = roomNo;
        this.name = name;
        this.totalRent = totalRent;
        this.rentPaid = rentPaid;
        this.balance = balance;
        this.timeStamp = timeStamp;
        this.month = month;
        this.rentStatus = rentStatus;
        this.comments = comments;
        this.electricityBill = electricityBill;
        this.waterBill = waterBill;
    }

    @Generated(hash = 558288959)
    public RentTransaction() {
    }

    public String getRoomNo() {
        return this.roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalRent() {
        return this.totalRent;
    }

    public void setTotalRent(int totalRent) {
        this.totalRent = totalRent;
    }

    public int getRentPaid() {
        return this.rentPaid;
    }

    public void setRentPaid(int rentPaid) {
        this.rentPaid = rentPaid;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public boolean getRentStatus() {
        return this.rentStatus;
    }

    public void setRentStatus(boolean rentStatus) {
        this.rentStatus = rentStatus;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getElectricityBill() {
        return this.electricityBill;
    }

    public void setElectricityBill(int electricityBill) {
        this.electricityBill = electricityBill;
    }

    public int getWaterBill() {
        return this.waterBill;
    }

    public void setWaterBill(int waterBill) {
        this.waterBill = waterBill;
    }

}
