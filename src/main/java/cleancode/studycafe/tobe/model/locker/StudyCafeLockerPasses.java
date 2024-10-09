package cleancode.studycafe.tobe.model.locker;

import cleancode.studycafe.tobe.model.pass.StudyCafePass;

import java.util.List;
import java.util.Optional;

public class StudyCafeLockerPasses {

    private final List<StudyCafeLockerPass> studyCafeLockerPasses;


    private StudyCafeLockerPasses(List<StudyCafeLockerPass> studyCafeLockerPasses) {
        this.studyCafeLockerPasses = studyCafeLockerPasses;
    }

    public static StudyCafeLockerPasses of(List<StudyCafeLockerPass> studyCafeLockerPasses) {
        return new StudyCafeLockerPasses(studyCafeLockerPasses);
    }

    public Optional<StudyCafeLockerPass> getStudyCafeLockerPass(StudyCafePass selectedPass) {

        if (selectedPass.useNotLocker()) {
            return Optional.empty();
        }

        return studyCafeLockerPasses.stream()
                .filter(option -> isSameTypeAndDuration(selectedPass, option))
                .findFirst();
    }

    private boolean isSameTypeAndDuration(StudyCafePass selectedPass, StudyCafeLockerPass option) {
        return option.getPassType() == selectedPass.getPassType()
                && option.getDuration() == selectedPass.getDuration();
    }

}
