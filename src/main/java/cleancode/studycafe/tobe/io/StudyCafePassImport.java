package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafePasses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudyCafePassImport implements StudyCafePass {

    public static final String STUDY_PASS_LIST_PATH = "src/main/resources/cleancode/studycafe/pass-list.csv";


    public StudyCafePasses getStudyCafePasses() {

        try {

            List<String> lines = Files.readAllLines(Paths.get(STUDY_PASS_LIST_PATH));
            List<cleancode.studycafe.tobe.model.pass.StudyCafePass> studyCafePasses = getStudyCafePasses(lines);

            return StudyCafePasses.of(studyCafePasses);
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }

    private List<cleancode.studycafe.tobe.model.pass.StudyCafePass> getStudyCafePasses(List<String> lines) {
        List<cleancode.studycafe.tobe.model.pass.StudyCafePass> studyCafePasses = new ArrayList<>();
        for (String line : lines) {
            String[] values = line.split(",");
            StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
            int duration = Integer.parseInt(values[1]);
            int price = Integer.parseInt(values[2]);
            double discountRate = Double.parseDouble(values[3]);

            cleancode.studycafe.tobe.model.pass.StudyCafePass studyCafePass = cleancode.studycafe.tobe.model.pass.StudyCafePass.of(studyCafePassType, duration, price, discountRate);
            studyCafePasses.add(studyCafePass);
        }
        return studyCafePasses;
    }

}

