package com.example.demo.queries;

public class ShipperQueries {
    public final static String GET_SHIPPERS = "SELECT * FROM shippers";
    public final static String GET_SHIPPERS_BY_ID = "SELECT * FROM shippers WHERE id = ?";
    public final static String GET_SHIPPERS_BY_NAME = "SELECT * FROM shippers WHERE companyName LIKE ?";

    public final static String ADD_SHIPPER = "INSERT INTO shippers(id, companyName) VALUES (?, ?)";

    public final static String UPDATE_SHIPPER = "UPDATE shippers SET companyName = ? WHERE id = ?";

    public final static String DELETE_SHIPPER = "DELETE from shippers WHERE id = ?";
}
