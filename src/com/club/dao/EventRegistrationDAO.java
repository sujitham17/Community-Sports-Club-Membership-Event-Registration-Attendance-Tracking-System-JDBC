package com.club.dao;

import java.sql.*;
import com.club.bean.EventRegistration;
import com.club.util.DBUtil;

public class EventRegistrationDAO {

    public int generateRegistrationID() throws Exception {
        Connection con = DBUtil.getDBConnection();
        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(
            "SELECT NVL(MAX(REGISTRATION_ID), 700000) + 1 FROM EVENT_REG_TBL"
        );

        rs.next();
        int id = rs.getInt(1);
        con.close();
        return id;
    }

    public boolean insertRegistration(EventRegistration r, Connection con) throws Exception {

        String sql = "INSERT INTO EVENT_REG_TBL VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, r.getRegistrationID());
        ps.setString(2, r.getMemberID());
        ps.setString(3, r.getEventName());
        ps.setString(4, r.getSportType());
        ps.setDate(5, r.getEventDate());
        ps.setString(6, r.getSessionSlot());
        ps.setString(7, r.getVenue());
        ps.setDate(8, r.getRegistrationDate());
        ps.setString(9, r.getAttendanceStatus());
        ps.setString(10, r.getResultStatus());

        return ps.executeUpdate() > 0;
    }
}
