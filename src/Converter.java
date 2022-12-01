import java.util.TreeMap;

public class Converter {
    TreeMap<Character, Integer> romanKeyMap = new TreeMap<>();
    TreeMap<Integer, String> arabicKeyMap = new TreeMap<>();

    Converter() {
        romanKeyMap.put('I', 1);
        romanKeyMap.put('V', 5);
        romanKeyMap.put('X', 10);
        romanKeyMap.put('L', 50);
        romanKeyMap.put('C', 100);

        arabicKeyMap.put(100, "C");
        arabicKeyMap.put(90, "XC");
        arabicKeyMap.put(50, "L");
        arabicKeyMap.put(40, "XL");
        arabicKeyMap.put(10, "X");
        arabicKeyMap.put(9, "IX");
        arabicKeyMap.put(5, "V");
        arabicKeyMap.put(4, "IV");
        arabicKeyMap.put(1, "I");

    }


    boolean isNumberSystem(String number) {
        return romanKeyMap.containsKey(number.charAt(0));
    }

    //15
    String arabicToRoman(int number) {
        StringBuilder roman = new StringBuilder();
        if (number <= 0) {
            return "0";

        } else {
            int arabianKey;
            do {
                arabianKey = arabicKeyMap.floorKey(number);
                roman.append(arabicKeyMap.get(arabianKey));
                number -= arabianKey;
            } while (number != 0);

        }
        return roman.toString();
    }

    int romanToArabic(String romanDigits) {
        int endDigits = romanDigits.length() - 1;
        char[] arrDigits = romanDigits.toCharArray();
        int arabicDigits;
        int result = romanKeyMap.get(arrDigits[endDigits]);

        for (int i = endDigits - 1; i >= 0; i--) {
            arabicDigits = romanKeyMap.get(arrDigits[i]);

            if (arabicDigits < romanKeyMap.get(arrDigits[i + 1])) {
                result -= arabicDigits;
            } else {
                result += arabicDigits;
            }
        }
        return result;
    }
}