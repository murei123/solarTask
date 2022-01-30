package Helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper
{
    private static final DateFormat _format = new SimpleDateFormat("dd.MM.yyyy");

    public String DateToString(Date date){
        return _format.format(date);
    }

    public Date StringToDate(String stringDate) {
        try {
            return _format.parse(stringDate);
        } catch (ParseException e) {
            System.out.println("Не удалось преобразовать дату");
        }
        return null;
    }
}
