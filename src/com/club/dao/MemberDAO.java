package com.club.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.club.bean.Member;
import com.club.util.DBUtil;

public class MemberDAO {

    public boolean insertMember(Member m) throws Exception {
        Connection con = DBUtil.getDBConnection();
        String sql = "INSERT INTO MEMBER_TBL VALUES (?,?,?,?,?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, m.getMemberID());
        ps.setString(2, m.getFullName());
        ps.setInt(3, m.getAge());
        ps.setString(4, m.getGender());
        ps.setString(5, m.getPrimarySport());
        ps.setString(6, m.getMobile());
        ps.setString(7, m.getEmail());
        ps.setString(8, m.getStatus());

        int rows = ps.executeUpdate();
        con.close();

        return rows > 0;
    }
    public Member findMemberById(String memberId) throws Exception {
        Connection con = DBUtil.getDBConnection();
        String sql = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, memberId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Member m = new Member();
            m.setMemberID(rs.getString("MEMBER_ID"));
            con.close();
            return m;
        }

        con.close();
        return null;
    }

}
