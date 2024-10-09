package cleancode.studycafe.tobe.model;

import cleancode.studycafe.tobe.model.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.pass.StudyCafePass;

public class StudyCafePassOrder {

    private final StudyCafePass selectedPass;

    private final StudyCafeLockerPass lockerPass;

    private StudyCafePassOrder(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass) {
        this.selectedPass = selectedPass;
        this.lockerPass = lockerPass;
    }

    public static StudyCafePassOrder of(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass) {
        return new StudyCafePassOrder(selectedPass, lockerPass);
    }

    public String studyTicketDisplay() {
        return selectedPass.display();
    }

    public String selectLockerDisplay() {
        return lockerPass.display();
    }

    public boolean existsLocker() {
        return lockerPass == null;
    }

    public double getDiscountRate(){
        return selectedPass.getDiscountRate();
    }

    public int getPrice(){
        return selectedPass.getPrice();
    }

}
