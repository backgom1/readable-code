package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.io.Locker;
import cleancode.studycafe.tobe.io.LockerFileImport;
import cleancode.studycafe.tobe.io.StudyCafePass;
import cleancode.studycafe.tobe.io.StudyCafePassImport;

public class StudyCafeApplication {

    public static void main(String[] args) {
        StudyCafePass studyCafePassProvider = new StudyCafePassImport();
        Locker lockerPassProvider = new LockerFileImport();
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(studyCafePassProvider,lockerPassProvider);
        studyCafePassMachine.run();
    }

}
