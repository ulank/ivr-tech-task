import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Ulan Kozhabekov on 5/3/2023
 */
public class IVR {

    /**
     * The console scanner used to read user input.
     */
    public static final Scanner CONSOLE = new Scanner(System.in);

    /**
     * The file extension for the audio files.
     */
    public static final String TYPE_FILE = ".wav";

    /**
     * The list of audio file names generated for the input number.
     */
    public static List<String> audios = new ArrayList<>();

    public static void main(String[] args) {
        int n = CONSOLE.nextInt();
        if (isValid(n)) {
            convertToIVR(n);

            for (int i = 0; i < audios.size(); i++) {
                if (i > 0) {
                    System.out.print(" + ");
                }
                System.out.print(audios.get(i));
            }

        } else {
            throw new RuntimeException("Out of bounds!");
        }
    }

    /**
     * Validates if the input number is within the allowed range of 0 to 9999.
     *
     * @param n the input number to validate
     * @return true if the number is within the allowed range, false otherwise
     */
    static boolean isValid(int n) {
        return n > -1 && n < 10000;
    }

    /**
     * Converts the input number to a list of audio file names
     * @param n the input number to convert
     */
    static void convertToIVR(int n) {
        int length = String.valueOf(n).length();
        while (length > 0) {
            int gap = (int) Math.pow(10, length - 1);
            if (n > 0) {
                int div = n / gap;
                if (length == 4) {
                    if (div == 1) {
                        audios.add(div + "na" + TYPE_FILE);
                        audios.add(gap + TYPE_FILE);
                    } else if (div == 2) {
                        audios.add(div + "ve" + TYPE_FILE);
                        audios.add(gap + "chi" + TYPE_FILE);
                    } else if (div < 5) {
                        audios.add(div + TYPE_FILE);
                        audios.add(gap + "chi" + TYPE_FILE);
                    } else {
                        audios.add(div + TYPE_FILE);
                        audios.add(gap + "ch" + TYPE_FILE);
                    }
                } else if (length == 3) {
                    if (div == 1) {
                        audios.add(gap + TYPE_FILE);
                    } else {
                        audios.add(div * gap + TYPE_FILE);
                    }
                } else if (length == 2) {
                    if (n < 20) {
                        audios.add(n + TYPE_FILE);
                        length--;
                    } else {
                        audios.add(div * gap + TYPE_FILE);
                    }
                } else {
                    audios.add(n + TYPE_FILE);
                }
            }
            n = (n % gap);
            length--;
        }
    }


}