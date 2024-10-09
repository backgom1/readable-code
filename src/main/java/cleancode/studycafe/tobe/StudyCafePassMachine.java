package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.Locker;
import cleancode.studycafe.tobe.io.StudyCafeInputHandler;
import cleancode.studycafe.tobe.io.StudyCafeOutputHandler;
import cleancode.studycafe.tobe.io.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassOrder;
import cleancode.studycafe.tobe.model.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.locker.StudyCafeLockerPasses;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafePasses;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final StudyCafeInputHandler studyCafeInputHandler = new StudyCafeInputHandler();
    private final StudyCafeOutputHandler studyCafeOutputHandler = new StudyCafeOutputHandler();

    private final StudyCafePass studyCafePassProvider;
    private final Locker lockerPassProvider;

    public StudyCafePassMachine(StudyCafePass studyCafePassProvider, Locker lockerPassProvider) {
        this.studyCafePassProvider = studyCafePassProvider;
        this.lockerPassProvider = lockerPassProvider;
    }

    public void run() {
        try {
            studyCafeOutputHandler.showWelcomeMessage();

            studyCafeOutputHandler.showAnnouncement();

            StudyCafePassType studyCafePassType = getStudyCafePassType();

            cleancode.studycafe.tobe.model.pass.StudyCafePass selectedPass = getStudyCafePass(studyCafePassType);

            Optional<StudyCafeLockerPass> optionalLockerPass = getStudyCafeLocker(selectedPass);

            StudyCafePassOrder studyCafePassOrder = StudyCafePassOrder.of(selectedPass, optionalLockerPass.orElse(null));

            studyCafeOutputHandler.showPassOrderSummary(studyCafePassOrder);
        } catch (AppException e) {
            studyCafeOutputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            studyCafeOutputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private cleancode.studycafe.tobe.model.pass.StudyCafePass getStudyCafePass(StudyCafePassType studyCafePassType) {
        List<cleancode.studycafe.tobe.model.pass.StudyCafePass> selectPassTicket = getStudyCafePasses(studyCafePassType);
        studyCafeOutputHandler.showPassListForSelection(selectPassTicket);
        return studyCafeInputHandler.getSelectPass(selectPassTicket);
    }

    private Optional<StudyCafeLockerPass> getStudyCafeLocker(cleancode.studycafe.tobe.model.pass.StudyCafePass selectedPass) {
        StudyCafeLockerPasses lockerPasses = lockerPassProvider.getLockerPass();
        Optional<StudyCafeLockerPass> lockerPass = lockerPasses.getStudyCafeLockerPass(selectedPass);

        if (lockerPass.isPresent()) {
            StudyCafeLockerPass cafeLockerPass = lockerPass.get();

            studyCafeOutputHandler.askLockerPass(cafeLockerPass);
            boolean isLockerSelected = studyCafeInputHandler.getLockerSelection();

            if (isLockerSelected) {
                return Optional.of(cafeLockerPass);
            }
        }

        return Optional.empty();
    }

    private StudyCafePassType getStudyCafePassType() {
        studyCafeOutputHandler.askPassTypeSelection();
        return studyCafeInputHandler.getPassTypeSelectingUserAction();
    }

    private List<cleancode.studycafe.tobe.model.pass.StudyCafePass> getStudyCafePasses(StudyCafePassType studyCafePassType) {
        StudyCafePasses studyCafePasses = studyCafePassProvider.getStudyCafePasses();
        return studyCafePasses.getTypeTicket(studyCafePassType);
    }

}
