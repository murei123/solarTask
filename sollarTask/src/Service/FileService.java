package Service;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import Helper.DateHelper;
import Module.Birthday;

public class FileService {

    private static final DateHelper _dateHelper = new DateHelper();

    public String WriteToFile(List<Birthday> list, String fileName) {

        try(FileWriter fileWriter = new FileWriter(fileName + ".txt", false))
        {
            StringBuilder textToWrite = new StringBuilder();
            for (Birthday item : list) {
                textToWrite
                        .append(item.getId())
                        .append("\t")
                        .append(item.getName())
                        .append("\t")
                        .append(_dateHelper.DateToString(item.getDr()))
                        .append("\n");
            }

            fileWriter.write(textToWrite.toString());
            fileWriter.flush();
        }
        catch(Exception ex){
            System.out.println("Fail Write");
        }
        return "Success Write";
    }

    public String ReadFromFile(List<Birthday> list, String fileName){
        list.clear();
        try {
            String read = new String(Files.readAllBytes(Paths.get(fileName + ".txt")));
            String[] tempDrsList = read.split("\n");

            for (String item : tempDrsList) {
                String[] tempDrsListItem = item.split("\t");
                list.add(
                        new Birthday(
                                Integer.parseInt(tempDrsListItem[0]),
                                tempDrsListItem[1],
                                _dateHelper.StringToDate(tempDrsListItem[2])));
            }
        }
        catch (Exception e) {
            return "Ошибка чтения";
        }
        return "Успешно прочитан";
    }
}
