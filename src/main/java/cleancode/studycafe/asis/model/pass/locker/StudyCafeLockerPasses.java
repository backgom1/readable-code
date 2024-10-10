package cleancode.studycafe.asis.model.pass.locker;

import cleancode.studycafe.asis.model.pass.StudyCafeSeatPass;

import java.util.List;
import java.util.Optional;

public class StudyCafeLockerPasses {

    private final List<StudyCafeLockerPass> lockerPasses;

    private StudyCafeLockerPasses(List<StudyCafeLockerPass> passes) {
        this.lockerPasses = passes;
    }

    public static StudyCafeLockerPasses of(List<StudyCafeLockerPass> passes) {
        return new StudyCafeLockerPasses(passes);
    }

    public Optional<StudyCafeLockerPass> findLockerPassBy(StudyCafeSeatPass selectedPass) {
        return lockerPasses.stream()
                .filter(selectedPass::isSameDurationType)
                .findFirst();
    }
}
