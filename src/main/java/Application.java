package HW;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;

//Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке, разделенные
//пробелом: Фамилия Имя Отчество, дата_рождения, номер_телефона, пол. Форматы данных: фамилия, имя, отчество - строки
//дата_рождения - строка формата dd.mm.yyyy, номер_телефона - целое беззнаковое число без форматирования пол - символ
//латиницей f или m.
//Критерии:
//-Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым, вернуть код
// ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.
//-Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. Если форматы
// данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные типы
// java и создать свои. Исключение должно быть корректно обработано, пользователювыведено сообщениесинформацией,что
// именно неверно.
//- Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку
// должны записаться полученные данные, вида <Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
//Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
//Не забудьте закрыть соединение с файлом.
//При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано, пользователь должен
//увидеть стектрейс ошибки.

public class Application {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws DataCountException, DataFormatException {
        String inputData = getInputData();
        String[] inputDataArray = inputData.split(" ");
        System.out.println(Arrays.toString(inputDataArray));
        int countExpected = 5;
        checkDataCount(inputDataArray, countExpected);
        checkData(inputDataArray);
        String data = arrayToString(inputDataArray);
        saveToFile(inputDataArray);
    }


    public static String getInputData() {
        String inputData;
        System.out.println("Enter data using a space: \n"
                    + "Last name, First name, Date of Birth (xx.xx.xxxx), Phone Number (digits only), Sex ('m' or 'f').");
        inputData = scanner.nextLine();
        return inputData;
    }

    public static void checkDataCount(String[] inputDataArray, int count) throws DataCountException {
        if (inputDataArray.length > count) {
            throw new DataCountException("You enter too much data");
        }
        if (inputDataArray.length < count) {
            throw new DataCountException("You enter not enough data");
        }
    }

    public static void checkData(String[] data) throws DataFormatException {
        for (int i = 0; i < 2; i++) {
            boolean isLetterString = data[i] != null && data[i].chars().allMatch(Character::isLetter);
            if (!isLetterString) {
                throw new DataFormatException("Incorrect Name format");
            }
        }

        DateFormat date = new SimpleDateFormat("dd.MM.yyyy");
        date.setLenient(false);
        try {
            date.parse(data[2]);
        } catch (ParseException e) {
            throw new DataFormatException("Incorrect date format");
        }

        if (data[3].length() != 10) {
            throw new DataFormatException("Incorrect Phone format");
        }

        if (!data[4].equals("m") && !data[4].equals("f")) {
            throw new DataFormatException("Incorrect Sex format");
        }
    }

    public static String arrayToString(String[] inputDataArray) {
        StringBuilder sb = new StringBuilder();
        for (String s : inputDataArray) {
            sb.append("<").append(s).append(">");
        }
        sb.append("\n");
        return sb.toString();
    }

    public static void saveToFile(String[] data) {
        StringBuilder sb = new StringBuilder();
        for (String str : data) {
            sb.append("<").append(str).append(">");
        }
        try (FileWriter writer = new FileWriter(new File(data[0] + ".txt"), true)) {
            writer.write(sb + "\n");
        } catch (IOException e) {
            System.err.println("Problem with writing a file");
            e.printStackTrace();
        }
    }
}


class DataCountException extends Exception {
    public DataCountException(String message) {
        super(message);
    }
}

class DataFormatException extends Exception {
    public DataFormatException(String message) {
        super(message);
    }
}
