package com.club.app;

import com.club.bean.Member;
import com.club.service.ClubService;

public class ClubMain {

    private static boolean ok;
	public static void main(String[] args) throws Exception {
    	
		java.sql.Date eventDate =
			    new java.sql.Date(System.currentTimeMillis() + 2L*24*60*60*1000);

			java.sql.Date regDate =
			    new java.sql.Date(System.currentTimeMillis());


    	System.out.println(ok ? "Event Registered" : "Failed");


        try {
        	boolean ok = ClubService.registerMemberForEvent(
        		    "MB1001",
        		    "Morning Yoga Session",
        		    "YOGA",
        		    eventDate,
        		    "06:00-07:00",
        		    "Community Hall",
        		    regDate
        		);

        		System.out.println(ok ? "Event Registered" : "Failed");

            ClubService service = new ClubService();

            Member m = new Member();
            m.setMemberID("MB3007");
            m.setFullName("Sujitha");
            m.setAge(22);
            m.setGender("FEMALE");
            m.setPrimarySport("YOGA");
            m.setMobile("9876543210");
            m.setEmail("sujitha@gmail.com");

            boolean result = service.registerNewMember(m);

            System.out.println(result ? "Member Added" : "Failed");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
