
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import Module.Birthday;
import Service.BirthdayService;
import Service.FileService;

public class Main {
    public static void main(String[] args) {
        BirthdayService _birthdayService = new BirthdayService();
        FileService _fileService = new FileService();
        Scanner _scanner = new Scanner(System.in);
        List<Birthday> birthdayList = new ArrayList<>();
        
        int choose;
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("-----------------------");
            System.out.println("1. Вывод списка");
            System.out.println("2. Дни рождения в этом месяце");
            System.out.println("3. Добавить запись");
            System.out.println("4. Удалять запись");
            System.out.println("5. Редактировать запись");
            System.out.println("6. Читать из файла");
            System.out.println("7. Сохранение файла");
            System.out.println("8. Выход");
            System.out.print(">");
            choose = _scanner.nextInt();

            switch (choose) {
                case 1 -> System.out.println(_birthdayService.GetStringBirthdayList(birthdayList));
                case 2 -> System.out.println(_birthdayService.GetStringCurrentBirthdayList(birthdayList));
                case 3 -> {
                    System.out.println("Введите имя");
                    String name = _scanner.next();
                    System.out.println("Введите дату рождения в формате ДД.ММ.ГГГГ");
                    String date = _scanner.next();
                    System.out.println(_birthdayService.AddBirthday(birthdayList, name, date));
                }
                case 4 -> {
                    System.out.println("Enter id");
                    int id = _scanner.nextInt();
                    System.out.println(_birthdayService.RemoveBirthday(birthdayList, id));
                }
                case 5 -> {
                    System.out.println("Enter id");
                    int id = _scanner.nextInt();
                    System.out.println("Enter Name");
                    String name = _scanner.next();
                    System.out.println("Enter Date");
                    String date = _scanner.next();
                    System.out.println(_birthdayService.EditBirthday(birthdayList,id,name,date));
                }
                case 6 -> {
                    System.out.println("Введите имя файла");
                    String fileName = _scanner.next();
                    System.out.println(_fileService.ReadFromFile(birthdayList,fileName));
                }
                case 7 -> {
                    System.out.println("Введите имя файла");
                    String fileName = _scanner.next();
                    System.out.println(_fileService.WriteToFile(birthdayList,fileName));
                }
                case 8 -> {
                    System.out.println("До встречи, спасибо, за использования программы");
                    return;
                }
                default -> isRunning = false;
            }
        }
    }
}
