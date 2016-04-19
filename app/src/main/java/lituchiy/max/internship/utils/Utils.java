package lituchiy.max.internship.utils;

import android.content.Context;
import android.text.format.DateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import lituchiy.max.internship.R;
import lituchiy.max.internship.data.Appeal;


public class Utils {

    private static Locale locale = Locale.getDefault();

    public static String getRelativeTimeSpanString(long milliseconds) {
        Date date = new Date(milliseconds);
        return DateUtils.getRelativeTimeSpanString(date.getTime()).toString();
    }

    public static String millisecondsToString(Context context, long milliseconds) {

        SimpleDateFormat df = new SimpleDateFormat(context.getString(R.string.dateFormat), locale);
        return df.format(new Date(milliseconds));
    }

    public static List<Appeal> getAppealsList(Context context) {
        List<Appeal> appeals = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int typeIndex = random.nextInt(Appeal.AppealType.values().length);
            Appeal.AppealType type = Appeal.AppealType.values()[typeIndex];
            String address = context.getString(R.string.address);
            String status = context.getString(R.string.status);
            long created = System.currentTimeMillis() - random.nextInt(10) * 86400000;
            long registered = created + random.nextInt(10) * 86400000;
            long assigned = registered + random.nextInt(10) * 86400000;
            int likes = random.nextInt(100);
            String responsible = context.getString(R.string.responsible_name);
            String description = context.getString(R.string.description_text);

            appeals.add(new Appeal(type, address, status, created, registered, assigned,
                    responsible, description, likes));
        }
        return appeals;
    }

}
