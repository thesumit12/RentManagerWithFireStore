package com.example.sumitlakra.rentmanager.data.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "RENTHISTORY")
public class RentHistory {

    @Id(autoincrement = true)
    private Long id;

    @Property
    private String roomNumber;

    @Property
    private String name;

    @Property
    private String rentPaid;

    @Property
    private String balance;

    @Property
    private String timeStamp;

    @Property
    private String month;

    public RentHistory(String roomNumber, String name, String rentPaid,
                       String balance, String timeStamp, String month) {
        this.roomNumber = roomNumber;
        this.name = name;
        this.rentPaid = rentPaid;
        this.balance = balance;
        this.timeStamp = timeStamp;
        this.month = month;
    }

    @Generated(hash = 731732266)
    public RentHistory(Long id, String roomNumber, String name, String rentPaid,
            String balance, String timeStamp, String month) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.name = name;
        this.rentPaid = rentPaid;
        this.balance = balance;
        this.timeStamp = timeStamp;
        this.month = month;
    }

    @Generated(hash = 920174727)
    public RentHistory() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRentPaid() {
        return this.rentPaid;
    }

    public void setRentPaid(String rentPaid) {
        this.rentPaid = rentPaid;
    }

    public String getBalance() {
        return this.balance;
    }

    public void setBalance(String balance) {
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

}
