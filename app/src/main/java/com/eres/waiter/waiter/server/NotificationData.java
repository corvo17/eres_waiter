package com.eres.waiter.waiter.server;

public class NotificationData {
    private long Id;
    private long FromUserId;
    private long ToUserId;
    private long TableId;
    private long HallId;
    private long OrderId;
    private long FromDepartmentId;
    private long ToDepartmentId;
    private String Message;
    private int NotificationTypeId;
    private String DateN;
    private long ProductId;

    public void setId(long id) {
        Id = id;
    }

    public void setFromUserId(long fromUserId) {
        FromUserId = fromUserId;
    }

    public void setToUserId(long toUserId) {
        ToUserId = toUserId;
    }

    public void setTableId(long tableId) {
        TableId = tableId;
    }

    public void setHallId(long hallId) {
        HallId = hallId;
    }

    public void setOrderId(long orderId) {
        OrderId = orderId;
    }

    public void setFromDepartmentId(long fromDepartmentId) {
        FromDepartmentId = fromDepartmentId;
    }

    public void setToDepartmentId(long toDepartmentId) {
        ToDepartmentId = toDepartmentId;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public void setNotificationTypeId(int notificationTypeId) {
        NotificationTypeId = notificationTypeId;
    }

    public void setDateN(String dateN) {
        DateN = dateN;
    }

    public void setProductId(long productId) {
        ProductId = productId;
    }

    public long getId() {
        return Id;
    }

    public long getFromUserId() {
        return FromUserId;
    }

    public long getToUserId() {
        return ToUserId;
    }

    public long getTableId() {
        return TableId;
    }

    public long getHallId() {
        return HallId;
    }

    public long getOrderId() {
        return OrderId;
    }

    public long getFromDepartmentId() {
        return FromDepartmentId;
    }

    public long getToDepartmentId() {
        return ToDepartmentId;
    }

    public String getMessage() {
        return Message;
    }

    public int getNotificationTypeId() {
        return NotificationTypeId;
    }

    public String getDateN() {
        return DateN;
    }

    public long getProductId() {
        return ProductId;
    }
}
