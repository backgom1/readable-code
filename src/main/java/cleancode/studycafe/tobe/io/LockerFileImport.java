package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.locker.StudyCafeLockerPasses;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LockerFileImport implements Locker {

    public static final String LOCKER_PATH = "src/main/resources/cleancode/studycafe/locker.csv";

    public StudyCafeLockerPasses getLockerPass() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(LOCKER_PATH));
            List<StudyCafeLockerPass> lockerPasses = getStudyCafeLockerPasses(lines);
            return StudyCafeLockerPasses.of(lockerPasses);
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }

    private List<StudyCafeLockerPass> getStudyCafeLockerPasses(List<String> lines) {
        List<StudyCafeLockerPass> lockerPasses = new ArrayList<>();
        for (String line : lines) {
            String[] values = line.split(",");
            StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
            int duration = Integer.parseInt(values[1]);
            int price = Integer.parseInt(values[2]);

            StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(studyCafePassType, duration, price);
            lockerPasses.add(lockerPass);
        }
        return lockerPasses;
    }
}
