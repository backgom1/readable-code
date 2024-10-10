package cleancode.studycafe.asis;

import cleancode.studycafe.asis.provider.LockerPassProvider;
import cleancode.studycafe.asis.provider.SeatPassProvider;

public class StudyCafeApplication {

    public static void main(String[] args) {
        SeatPassProvider seatPassProvider = new SeatPassFileReader();
        LockerPassProvider lockerPassProvider = new LockerPassFileReader();
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(seatPassProvider,lockerPassProvider);
        studyCafePassMachine.run();
    }

}
