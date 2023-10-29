package sit.int202.simplefri;

import java.util.Date;

public class TestTime {
    public static void main(String[] args) {
        Date d1 = new Date(1000*60); // 1000 = 1sec * 60 = 60sec = 1m
        Date d2 = new Date(1000*60*60*24); // 1m * 60 = 60m , 60m * 24 = 1day
        System.out.println(d1);
        System.out.println(d2);
        // Thu Jan 01 07:01:00 ICT 1970
        // Fri Jan 02 07:00:00 ICT 1970
        System.out.println(System.currentTimeMillis());
        Date today = new Date(1698438657557L);
        Date tomorrow = new Date(1698438657557L + 1000*60*60*24);
        System.out.println(today);
        System.out.println(tomorrow);
    }
}
