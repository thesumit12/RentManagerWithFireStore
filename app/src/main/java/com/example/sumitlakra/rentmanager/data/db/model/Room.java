package com.example.sumitlakra.rentmanager.data.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

@Entity(nameInDb = "room")
public class Room {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "imageId")
    private int imageId;

    @Property(nameInDb = "roomNumber")
    @Unique
    private String roomNumber;

    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "age")
    private int age;

    @Property(nameInDb = "totalMembers")
    private int totalMembers;

    @Property(nameInDb = "adults")
    private int adults;

    @Property(nameInDb = "roomRent")
    private int roomRent;

    @Property(nameInDb = "roomReading")
    private int roomReading;

    @Property(nameInDb = "mainMeterReading")
    private int mainMeterReading;

    @Property(nameInDb = "rentDue")
    private int rentDue;

    @Property(nameInDb = "totalRent")
    private int totalRent;

    @Property(nameInDb = "comments")
    private String comments;

    @Property(nameInDb = "month")
    private String month;

    @Property(nameInDb = "tempMainMeterReading")
    private int tempMainMeterReading;

    @Property(nameInDb = "tempRoomReading")
    private int tempRoomReading;

    @Property(nameInDb = "startMonth")
    private String startMonth;

    @Property
    private int roomNoInt;

    public Room(int imageId, String roomNumber){
        this.imageId = imageId;
        this.roomNumber = roomNumber;
    }

    public Room(int imageId,String roomNumber, String name, int age,
                int totalMembers, int adults, int roomRent, int lastReading,
                int mainMeterReading, int rentDue, int totalRent, String comments, String month,
                String startMonth, int roomNoInt) {
        this.imageId = imageId;
        this.roomNumber = roomNumber;
        this.name = name;
        this.age = age;
        this.totalMembers = totalMembers;
        this.adults = adults;
        this.roomRent = roomRent;
        this.roomReading = lastReading;
        this.mainMeterReading = mainMeterReading;
        this.rentDue = rentDue;
        this.totalRent = totalRent;
        this.comments = comments;
        this.month = month;
        this.startMonth = startMonth;
        this.roomNoInt = roomNoInt;
    }

    public Room(String roomNumber, String name, int age,
                int totalMembers, int adults, int roomRent, int lastReading,
                int mainMeterReading, int rentDue) {
        this.roomNumber = roomNumber;
        this.name = name;
        this.age = age;
        this.totalMembers = totalMembers;
        this.adults = adults;
        this.roomRent = roomRent;
        this.roomReading = lastReading;
        this.mainMeterReading = mainMeterReading;
        this.rentDue = rentDue;
    }

    public Room(String name, int totalMembers, String comments, int rentDue){
        this.name = name;
        this.totalMembers = totalMembers;
        this.comments = comments;
        this.rentDue = rentDue;
    }

    @Generated(hash = 1487817844)
    public Room(Long id, int imageId, String roomNumber, String name, int age, int totalMembers,
            int adults, int roomRent, int roomReading, int mainMeterReading, int rentDue, int totalRent,
            String comments, String month, int tempMainMeterReading, int tempRoomReading,
            String startMonth, int roomNoInt) {
        this.id = id;
        this.imageId = imageId;
        this.roomNumber = roomNumber;
        this.name = name;
        this.age = age;
        this.totalMembers = totalMembers;
        this.adults = adults;
        this.roomRent = roomRent;
        this.roomReading = roomReading;
        this.mainMeterReading = mainMeterReading;
        this.rentDue = rentDue;
        this.totalRent = totalRent;
        this.comments = comments;
        this.month = month;
        this.tempMainMeterReading = tempMainMeterReading;
        this.tempRoomReading = tempRoomReading;
        this.startMonth = startMonth;
        this.roomNoInt = roomNoInt;
    }

    @Generated(hash = 703125385)
    public Room() {
    }



    

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getImageId() {
        return this.imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
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

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTotalMembers() {
        return this.totalMembers;
    }

    public void setTotalMembers(int totalMembers) {
        this.totalMembers = totalMembers;
    }

    public int getAdults() {
        return this.adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getRoomRent() {
        return this.roomRent;
    }

    public void setRoomRent(int roomRent) {
        this.roomRent = roomRent;
    }

    public int getLastReading() {
        return this.roomReading;
    }

    public void setLastReading(int lastReading) {
        this.roomReading = lastReading;
    }

    public int getRentDue() {
        return this.rentDue;
    }

    public void setRentDue(int rentDue) {
        this.rentDue = rentDue;
    }

    public int getTotalRent() {
        return this.totalRent;
    }

    public void setTotalRent(int totalRent) {
        this.totalRent = totalRent;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getRoomReading() {
        return this.roomReading;
    }

    public void setRoomReading(int roomReading) {
        this.roomReading = roomReading;
    }

    public int getMainMeterReading() {
        return this.mainMeterReading;
    }

    public void setMainMeterReading(int mainMeterReading) {
        this.mainMeterReading = mainMeterReading;
    }

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getTempMainMeterReading() {
        return this.tempMainMeterReading;
    }

    public void setTempMainMeterReading(int tempMainMeterReading) {
        this.tempMainMeterReading = tempMainMeterReading;
    }

    public int getTempRoomReading() {
        return this.tempRoomReading;
    }

    public void setTempRoomReading(int tempRoomReading) {
        this.tempRoomReading = tempRoomReading;
    }

    public String getStartMonth() {
        return this.startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public int getRoomNoInt() {
        return this.roomNoInt;
    }

    public void setRoomNoInt(int roomNoInt) {
        this.roomNoInt = roomNoInt;
    }
}
