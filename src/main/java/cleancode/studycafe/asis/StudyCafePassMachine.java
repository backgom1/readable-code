package cleancode.studycafe.asis;

import cleancode.studycafe.asis.exception.AppException;
import cleancode.studycafe.asis.io.StudyCafeIOHandler;
import cleancode.studycafe.asis.model.*;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final StudyCafeIOHandler studyCafeIOHandler = new StudyCafeIOHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();


    public void run() {
        try {
            studyCafeIOHandler.showWelcomeMessage();
            studyCafeIOHandler.showAnnouncement();

            StudyCafePass selectedPass = selectPass();
            Optional<StudyCafeLockerPass> optionalLockerPass = selectLockerPass(selectedPass);

            optionalLockerPass.ifPresentOrElse(
                    lockerPass -> studyCafeIOHandler.showPassOrderSummary(selectedPass, lockerPass),
                    () -> studyCafeIOHandler.showPassOrderSummary(selectedPass)
            );


        } catch (AppException e) {
            studyCafeIOHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            studyCafeIOHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }


    private StudyCafePass selectPass() {
        StudyCafePassType studyCafePassType = studyCafeIOHandler.askPassTypeSelecting();
        List<StudyCafePass> passCandidates = findPassCandidatesBy(studyCafePassType);
        return studyCafeIOHandler.askPassSelecting(passCandidates);
    }

    private List<StudyCafePass> findPassCandidatesBy(StudyCafePassType studyCafePassType) {
       StudyCafePasses allPasses = studyCafeFileHandler.readStudyCafePasses();
        return allPasses.findPassBy(studyCafePassType);
    }

    private Optional<StudyCafeLockerPass> selectLockerPass(StudyCafePass selectedPass) {

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

    private Optional<StudyCafeLockerPass> findPassLockerBy(StudyCafePass selectedPass) {
        StudyCafeLockerPasses lockerPasses = studyCafeFileHandler.readLockerPasses();
        return lockerPasses.findLockerPassBy(selectedPass);
    }

}
