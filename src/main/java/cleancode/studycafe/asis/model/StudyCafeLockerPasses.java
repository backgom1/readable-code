package cleancode.studycafe.asis.model;

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

    public Optional<StudyCafeLockerPass> findLockerPassBy(StudyCafePass selectedPass) {
        return lockerPasses.stream()
                .filter(selectedPass::isSameDurationType)
                .findFirst();
    }
}
