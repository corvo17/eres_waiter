package com.eres.waiter.waiter.viewpager.model;

import java.util.ArrayList;
import java.util.Date;


public class ERESNotification
{
    public static ArrayList<ERESNotification> Messages=new ArrayList<>();
    private long Id ;
    private long FromSmartPhoneId ;
    private long ToSmartPhoneId ;
    private long TableId ;
    private long HallId ;
    private long OrderId ;
    private long FromDepartmentId ;
    private long FromEmployeeId ;
    private String Message ;
    private int NotificationTypeId ;
    private Date DateN ;
    private long ProductId ;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getFromSmartPhoneId() {
        return FromSmartPhoneId;
    }

    public void setFromSmartPhoneId(long fromSmartPhoneId) {
        FromSmartPhoneId = fromSmartPhoneId;
    }

    public long getToSmartPhoneId() {
        return ToSmartPhoneId;
    }

    public void setToSmartPhoneId(long toSmartPhoneId) {
        ToSmartPhoneId = toSmartPhoneId;
    }

    public long getTableId() {
        return TableId;
    }

    public void setTableId(long tableId) {
        TableId = tableId;
    }

    public long getHallId() {
        return HallId;
    }

    public void setHallId(long hallId) {
        HallId = hallId;
    }

    public long getOrderId() {
        return OrderId;
    }

    public void setOrderId(long orderId) {
        OrderId = orderId;
    }

    public long getFromDepartmentId() {
        return FromDepartmentId;
    }

    public void setFromDepartmentId(long fromDepartmentId) {
        FromDepartmentId = fromDepartmentId;
    }

    public long getFromEmployeeId() {
        return FromEmployeeId;
    }

    public void setFromEmployeeId(long fromEmployeeId) {
        FromEmployeeId = fromEmployeeId;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getNotificationTypeId() {
        return NotificationTypeId;
    }

    public void setNotificationTypeId(int notificationTypeId) {
        NotificationTypeId = notificationTypeId;
    }

    public Date getDateN() {
        return DateN;
    }

    public void setDateN(Date dateN) {
        DateN = dateN;
    }

    public long getProductId() {
        return ProductId;
    }

    public void setProductId(long productId) {
        ProductId = productId;
    }
}