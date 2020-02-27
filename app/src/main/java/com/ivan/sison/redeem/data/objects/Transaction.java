package com.ivan.sison.redeem.data.objects;

import com.ivan.sison.redeem.data.entities.Event;
import com.ivan.sison.redeem.data.entities.Ticket;

public class Transaction {

    private String transactionId;
    private String transactionDate;
    private String tType;
    private String usedDate;
    private String lastAccessed;
    private String remark;
    private Event event;
    private Ticket ticket;

    public Transaction(String transactionId, String transactionDate, String tType, String usedDate,
                       String lastAccessed, String remark, Event event, Ticket ticket) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.tType = tType;
        this.usedDate = usedDate;
        this.lastAccessed = lastAccessed;
        this.remark = remark;
        this.event = event;
        this.ticket = ticket;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String gettType() {
        return tType;
    }

    public String getUsedDate() {
        return usedDate;
    }

    public String getLastAccessed() {
        return lastAccessed;
    }

    public String getRemark() {
        return remark;
    }

    public Event getEvent() {
        return event;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
