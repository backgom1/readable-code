package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.model.pass.StudyCafePass;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;

import java.util.List;
import java.util.Scanner;

public class StudyCafeInputHandler {

    public static final String HOURLY = "1";
    public static final String WEEKLY = "2";
    public static final String FIXED = "3";
    private static final Scanner SCANNER = new Scanner(System.in);

    public StudyCafePassType getPassTypeSelectingUserAction() {
        return getPassType();
    }

    private StudyCafePassType getPassType() {
        String userInput = SCANNER.nextLine();

        if (HOURLY.equals(userInput)) {
            return StudyCafePassType.HOURLY;
        }

        if (WEEKLY.equals(userInput)) {
            return StudyCafePassType.WEEKLY;
        }

        if (FIXED.equals(userInput)) {
            return StudyCafePassType.FIXED;
        }

        throw new AppException("잘못된 입력입니다.");
    }

    public StudyCafePass getSelectPass(List<StudyCafePass> passes) {
        String userInput = SCANNER.nextLine();
        int selectedIndex = Integer.parseInt(userInput) - 1;
        return passes.get(selectedIndex);
    }

    public boolean getLockerSelection() {
        String userInput = SCANNER.nextLine();
        return "1".equals(userInput);
    }

}
