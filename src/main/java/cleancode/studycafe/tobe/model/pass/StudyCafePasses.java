package cleancode.studycafe.tobe.model.pass;

import java.util.List;

//일급 컬렉션 사용
public class StudyCafePasses {

    List<StudyCafePass> studyCafePasses;

    private StudyCafePasses(List<StudyCafePass> studyCafePasses) {
        this.studyCafePasses = studyCafePasses;
    }

    public static StudyCafePasses of(List<StudyCafePass> studyCafePasses) {
        return new StudyCafePasses(studyCafePasses);
    }

    public List<StudyCafePass> getTypeTicket(StudyCafePassType type) {
        return studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.isSamePassType(type))
                .toList();
    }
}
