package com.eres.waiter.waiter.server;

public class NotificationData {
    public int Id;
    public int FromSmartPhoneId;
    public int ToSmartPhoneId;
    public int TableId;
    public int HallId;
    public int OrderId;
    public int FromDepartmentId;
    public int FromEmployeeId;
    public String Message;
    public int NotificationTypeId;
    public String DateN;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getFromSmartPhoneId() {
        return FromSmartPhoneId;
    }

    public void setFromSmartPhoneId(int fromSmartPhoneId) {
        FromSmartPhoneId = fromSmartPhoneId;
    }

    public int getToSmartPhoneId() {
        return ToSmartPhoneId;
    }

    public void setToSmartPhoneId(int toSmartPhoneId) {
        ToSmartPhoneId = toSmartPhoneId;
    }

    public int getTableId() {
        return TableId;
    }

    public void setTableId(int tableId) {
        TableId = tableId;
    }

    public int getHallId() {
        return HallId;
    }

    public void setHallId(int hallId) {
        HallId = hallId;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public int getFromDepartmentId() {
        return FromDepartmentId;
    }

    public void setFromDepartmentId(int fromDepartmentId) {
        FromDepartmentId = fromDepartmentId;
    }

    public int getFromEmployeeId() {
        return FromEmployeeId;
    }

    public void setFromEmployeeId(int fromEmployeeId) {
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

    public String getDateN() {
        return DateN;
    }

    public void setDateN(String dateN) {
        DateN = dateN;
    }
}
