package java8;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FullBizNumberDump {

    private static final int[] WEIGHTS = {1, 3, 7, 1, 3, 7, 1, 3, 5};
    private static final int PER_FILE = 1_000_000;

    public static void main(String[] args) throws IOException {
        int count = 0;
        int fileSeq = 0;
        BufferedWriter writer = null;
        String firstBizNumberInFile = null;

        for (int i = 0; i < 1_000_000_000; i++) {
            String base = String.format("%09d", i);
            String bizNumber = computeValidBizNumber(base);
            if (bizNumber != null) {
                if (count % PER_FILE == 0) {
                    if (writer != null) writer.close();
                    firstBizNumberInFile = bizNumber;
                    String fileName = firstBizNumberInFile + ".txt";
                    writer = new BufferedWriter(new FileWriter(fileName));
                    System.out.println("✔ 파일 시작: " + fileName);
                }

                writer.write(bizNumber);
                writer.newLine();
                count++;
            }
        }

        if (writer != null) writer.close();
        System.out.println("🎉 완료: 총 " + count + "개의 유효 사업자번호 생성 및 저장됨.");
    }

    private static String computeValidBizNumber(String nineDigits) {
        int[] digits = new int[10];
        for (int i = 0; i < 9; i++) {
            digits[i] = nineDigits.charAt(i) - '0';
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += digits[i] * WEIGHTS[i];
        }
        sum += (digits[8] * 5) / 10;

        digits[9] = (10 - (sum % 10)) % 10;

        // 전체 10자리를 포맷
        return String.format("%d%d%d-%d%d-%d%d%d%d%d",
                digits[0], digits[1], digits[2],
                digits[3], digits[4],
                digits[5], digits[6], digits[7], digits[8], digits[9]);
    }
}
