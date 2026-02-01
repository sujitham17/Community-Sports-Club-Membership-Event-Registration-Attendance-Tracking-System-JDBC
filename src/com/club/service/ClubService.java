package com.club.service;

import java.sql.Connection;
import java.sql.Date;

import com.club.bean.EventRegistration;
import com.club.bean.Member;
import com.club.dao.EventRegistrationDAO;
import com.club.dao.MemberDAO;
import com.club.util.DBUtil;

public class ClubService {

	public boolean registerNewMember(Member m) throws Exception {

	    MemberDAO dao = new MemberDAO();

	    if (dao.findMemberById(m.getMemberID()) != null) {
	        System.out.println("Member already exists!");
	        return false;
	    }

	    m.setStatus("ACTIVE");
	    return dao.insertMember(m);
	}

    public static boolean registerMemberForEvent(
            String memberID,
            String eventName,
            String sportType,
            java.sql.Date eventDate,
            String sessionSlot,
            String venue,
            java.sql.Date regDate) throws Exception {

        Connection con = null;

        try {
            con = DBUtil.getDBConnection();
            con.setAutoCommit(false);

            EventRegistrationDAO dao = new EventRegistrationDAO();

            int regId = dao.generateRegistrationID();

            EventRegistration r = new EventRegistration();
            r.setRegistrationID(regId);
            r.setMemberID(memberID);
            r.setEventName(eventName);
            r.setSportType(sportType);
            r.setEventDate(eventDate);
            r.setSessionSlot(sessionSlot);
            r.setVenue(venue);
            r.setRegistrationDate(regDate);
            r.setAttendanceStatus("REGISTERED");
            r.setResultStatus(null);

            boolean inserted = dao.insertRegistration(r, con);

            if (inserted) {
                con.commit();      
                return true;
            } else {
                con.rollback();   
                return false;
            }

        } catch (Exception e) {
            if (con != null) con.rollback();
            throw e;
        }
    }

}
