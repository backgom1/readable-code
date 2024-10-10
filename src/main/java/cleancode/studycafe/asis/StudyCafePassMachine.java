package cleancode.studycafe.asis;

import cleancode.studycafe.asis.exception.AppException;
import cleancode.studycafe.asis.io.StudyCafeIOHandler;
import cleancode.studycafe.asis.model.StudyCafePassOrder;
import cleancode.studycafe.asis.model.pass.StudyCafePassType;
import cleancode.studycafe.asis.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.asis.model.pass.StudyCafeSeatPasses;
import cleancode.studycafe.asis.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.asis.model.pass.locker.StudyCafeLockerPasses;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final StudyCafeIOHandler studyCafeIOHandler = new StudyCafeIOHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();


    public void run() {
        try {
            studyCafeIOHandler.showWelcomeMessage();
            studyCafeIOHandler.showAnnouncement();

            StudyCafeSeatPass selectedPass = selectPass();
            Optional<StudyCafeLockerPass> optionalLockerPass = selectLockerPass(selectedPass);

            StudyCafePassOrder studyCafePassOrder = StudyCafePassOrder.of(
                    selectedPass,
                    optionalLockerPass.orElse(null)
            );

        studyCafeIOHandler.showPassOrderSummary(studyCafePassOrder);


        } catch (AppException e) {
            studyCafeIOHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            studyCafeIOHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }


    private StudyCafeSeatPass selectPass() {
        StudyCafePassType studyCafePassType = studyCafeIOHandler.askPassTypeSelecting();
        List<StudyCafeSeatPass> passCandidates = findPassCandidatesBy(studyCafePassType);
        return studyCafeIOHandler.askPassSelecting(passCandidates);
    }

    private List<StudyCafeSeatPass> findPassCandidatesBy(StudyCafePassType studyCafePassType) {
       StudyCafeSeatPasses allPasses = studyCafeFileHandler.readStudyCafePasses();
        return allPasses.findPassBy(studyCafePassType);
    }

    private Optional<StudyCafeLockerPass> selectLockerPass(StudyCafeSeatPass selectedPass) {

        //고정 좌석 타입이 아닌가?
        // 사물함 옵션을 사용할 수 있는 타입이 아닌가?
        if (selectedPass.cannotUseLocker()) {
            return Optional.empty();
        }

        Optional<StudyCafeLockerPass> lockerPassCandidate = findPassLockerBy(selectedPass);

        if (lockerPassCandidate.isPresent()) {
            StudyCafeLockerPass studyCafeLockerPass = lockerPassCandidate.get();
            boolean isLockerSelected = studyCafeIOHandler.askLockPass(studyCafeLockerPass);
            if (isLockerSelected) {
                return Optional.of(studyCafeLockerPass);
            }
        }

        return Optional.empty();
    }

    private Optional<StudyCafeLockerPass> findPassLockerBy(StudyCafeSeatPass selectedPass) {
        StudyCafeLockerPasses lockerPasses = studyCafeFileHandler.readLockerPasses();
        return lockerPasses.findLockerPassBy(selectedPass);
    }

}
