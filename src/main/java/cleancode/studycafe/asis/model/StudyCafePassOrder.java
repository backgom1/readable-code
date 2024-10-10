package cleancode.studycafe.asis.model;

import cleancode.studycafe.asis.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.asis.model.pass.locker.StudyCafeLockerPass;

import java.util.Optional;

public class StudyCafePassOrder {

    private final StudyCafeSeatPass seatPass;
    private final StudyCafeLockerPass lockerPass;


    private StudyCafePassOrder(StudyCafeSeatPass seatPass, StudyCafeLockerPass lockerPass) {
        this.seatPass = seatPass;
        this.lockerPass = lockerPass;
    }

    public static StudyCafePassOrder of(StudyCafeSeatPass seatPass, StudyCafeLockerPass lockerPass){
        return new StudyCafePassOrder(seatPass, lockerPass);
    }

    public int getDisCountPrice() {
        return seatPass.getDiscountPrice();

    }

    public int getTotalPrice() {
        int lockerPassPrice = lockerPass != null ? lockerPass.getPrice() : 0;
        int totalPassPrice = seatPass.getPrice() + lockerPassPrice;
        return totalPassPrice - getDisCountPrice();
    }

    public StudyCafeSeatPass getSeatPass() {
        return this.seatPass;
    }

    public Optional<StudyCafeLockerPass> getLockPass() {
        return Optional.ofNullable(this.lockerPass);
    }

}
