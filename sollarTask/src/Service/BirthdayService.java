package Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import Helper.DateHelper;
import Module.Birthday;

public class BirthdayService {

    private final DateHelper _dateHelper = new DateHelper();

    public String GetStringBirthdayList(List<Birthday> birthdayList) {
        StringBuilder birthdayString = new StringBuilder();

        if (birthdayList.isEmpty()) {
           birthdayString.append("Список дней рождения пуст");
        } else {
            for (Birthday item : birthdayList) {
                birthdayString.append(String.format("%d\t%s\t%s%n", item.getId(), item.getName(), _dateHelper.DateToString(item.getDr())));
            }
        }

        return birthdayString.toString();
    }

    public String GetStringCurrentBirthdayList(List<Birthday> birthdayList) {

        StringBuilder birthdayString;

        if (birthdayList.isEmpty()) {
            birthdayString = new StringBuilder("Список дней рождения пуст");
        }
        else {
            List<Birthday> sortedBirthdayList = BirthdaySortByDate(birthdayList);
            birthdayString = new StringBuilder("Сегоднешний день рождения::\n");

            Calendar calCurrentDate = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"));
            calCurrentDate.setTime(new Date());
            Calendar calTemp = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"));


            for (Birthday item : sortedBirthdayList) {
                calTemp.setTime(item.getDr());
                if (calCurrentDate.get(Calendar.MONTH) == calTemp.get(Calendar.MONTH) &&
                        calCurrentDate.get(Calendar.DAY_OF_MONTH) == calTemp.get(Calendar.DAY_OF_MONTH) ) {
                    birthdayString
                            .append(BirthdayToString(item));
                }
            }
            birthdayString.append("Дни рождения в этом месяце:\n");

            for (Birthday item : sortedBirthdayList) {
                calTemp.setTime(item.getDr());
                if (calCurrentDate.get(Calendar.MONTH) == calTemp.get(Calendar.MONTH) &&
                        calCurrentDate.get(Calendar.DAY_OF_MONTH) < calTemp.get(Calendar.DAY_OF_MONTH) ) {
                    birthdayString
                            .append(BirthdayToString(item));
                }
            }
        }

        return birthdayString.toString();
    }

    private String BirthdayToString(Birthday birthday){
        StringBuilder birthdayString = new StringBuilder();
        return  birthdayString
                .append(birthday.getId())
                .append("\t")
                .append(birthday.getName())
                .append("\t")
                .append(_dateHelper.DateToString(birthday.getDr()))
                .append("\n")
                .toString();
    }
    private List<Birthday> BirthdaySortByDate(List<Birthday> birthdayList){
        return birthdayList.stream().sorted(
                (a, b) ->
                {
                    try {
                        return a.getDr().compareTo(b.getDr());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 0;
                }
        ).toList();
    }

    private List<Birthday> BirthdaySortById(List<Birthday> birthdayList){
        return birthdayList.stream().sorted(
                (a, b) ->
                {
                    try {
                        return a.getId() > b.getId() ? 1 : -1;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 0;
                }
        ).toList();
    }

    public String AddBirthday(List<Birthday> birthdayList, String name, String date) {
        Date dr = _dateHelper.StringToDate(date);
        List<Birthday> sortedBirthdayList = BirthdaySortById(birthdayList);
        int id = (sortedBirthdayList.get(sortedBirthdayList.size() - 1)).getId() + 1;
        birthdayList.add(new Birthday(id, name, dr));
        return "День рождения удачно добавлен";
    }

    public String  RemoveBirthday(List<Birthday> birthdayList, int id) {
        Birthday toRemove = birthdayList.stream()
                .filter(item -> item.getId() == id)
                .findFirst().orElse(null);
        return birthdayList.remove(toRemove)
                ? "День рождение удачно удалено"
                : "День рождение не найдено";
    }

    public String EditBirthday(List<Birthday> birthdayList, int id, String name, String date) {
        Date dr = _dateHelper.StringToDate(date);
        Birthday toEdit = birthdayList.stream()
                .filter(item -> item.getId() == id)
                .findFirst().orElse(null);

        if (toEdit == null) return "Нет данных с таким id";

        toEdit.setName(name);
        toEdit.setDr(dr);
        return "Данные успешно обновлены";
    }
}
