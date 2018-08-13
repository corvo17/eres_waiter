package com.eres.waiter.waiter.model;

import com.google.gson.annotations.SerializedName;

public class Data {
    public String getUrl() {
        return url;
    }

    public Data(String unicalNumber, int isBanned, int stateId, int id, int departmentId, int employeeId, int roleId, Object name, String url) {
        this.unicalNumber = unicalNumber;
        this.isBanned = isBanned;
        this.stateId = stateId;
        this.id = id;
        this.departmentId = departmentId;
        this.employeeId = employeeId;
        this.roleId = roleId;
        this.name = name;
    }

    private String url;
    @SerializedName("UnicalNumber")
    private String unicalNumber;

    @SerializedName("IsBanned")
    private int isBanned;

    @SerializedName("StateId")
    private int stateId;

    @SerializedName("Id")
    private int id;

    @SerializedName("DepartmentId")
    private int departmentId;

    @SerializedName("EmployeeId")
    private int employeeId;

    @SerializedName("RoleId")
    private int roleId;

    @SerializedName("Name")
    private Object name;

    public void setUnicalNumber(String unicalNumber) {
        this.unicalNumber = unicalNumber;
    }

    public String getUnicalNumber() {
        return unicalNumber;
    }

    public void setIsBanned(int isBanned) {
        this.isBanned = isBanned;
    }

    public int getIsBanned() {
        return isBanned;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public int getStateId() {
        return stateId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getName() {
        return name;
    }

    @Override
    public String toString() {
        return
                "Data{" +
                        "unicalNumber = '" + unicalNumber + '\'' +
                        ",isBanned = '" + isBanned + '\'' +
                        ",stateId = '" + stateId + '\'' +
                        ",id = '" + id + '\'' +
                        ",departmentId = '" + departmentId + '\'' +
                        ",employeeId = '" + employeeId + '\'' +
                        ",roleId = '" + roleId + '\'' +
                        ",name = '" + name + '\'' +
                        "}";
    }
}