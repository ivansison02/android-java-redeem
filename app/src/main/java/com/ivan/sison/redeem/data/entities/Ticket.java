package com.ivan.sison.redeem.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tickets")
public class Ticket {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userId;
    private String section;
    private String row;
    private String seat;
    private String ticketType;
    private String discountType;
    private String discountAmt;
    private String price;
    private String branch;
    private String status;


}
