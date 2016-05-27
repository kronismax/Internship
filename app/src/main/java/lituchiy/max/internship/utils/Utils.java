package lituchiy.max.internship.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.RealmList;
import lituchiy.max.internship.R;
import lituchiy.max.internship.data.AppealPhotoRealm;
import lituchiy.max.internship.data.AppealRealm;
import lituchiy.max.internship.data.model.Appeal;
import lituchiy.max.internship.data.model.Files;


public class Utils {

    private static Locale locale = Locale.getDefault();

    public static String millisecondsToString(Context context, long milliseconds) {
        SimpleDateFormat df = new SimpleDateFormat(context.getString(R.string.dateFormat), locale);
        return df.format(new Date(milliseconds * 1000));
    }

    public static AppealRealm appealToRealmObject(Appeal appeal) {
        AppealRealm appealRealm = new AppealRealm();
        appealRealm.setId(appeal.getId());
        appealRealm.setTitle(appeal.getTitle());
        appealRealm.setCreatedDate(appeal.getCreatedDate());
        appealRealm.setRegisteredDate(appeal.getCreatedDate());
        appealRealm.setAssignedDate(appeal.getStartDate());
        appealRealm.setCategory(appeal.getCategory().getName());
        appealRealm.setTitle(appeal.getTitle());
        appealRealm.setLikes(appeal.getLikesCounter());
        appealRealm.setDescription(appeal.getBody());
        appealRealm.setState(appeal.getState().getId());
        appealRealm.setStateName(appeal.getState().getName());

        if (appeal.getUser().getAddress() != null && appeal.getUser().getAddress().getStreet() != null) {
            appealRealm.setAddress(appeal.getUser().getAddress().toString());
        }

        if (appeal.getPerformers() != null && appeal.getPerformers().size() > 0) {
            appealRealm.setResponsible(appeal.getPerformers().get(0).getOrganization());
        }

        if (appeal.getFiles() != null && appeal.getFiles().size() > 0) {
            RealmList<AppealPhotoRealm> list = new RealmList<>();
            for (Files files : appeal.getFiles()) {
                AppealPhotoRealm photoRealm = new AppealPhotoRealm();
                photoRealm.setImageUrl(files.getFilename());
                list.add(photoRealm);
            }
            appealRealm.setPhoto(list);
        }
        return appealRealm;
    }

    public static boolean isConnectingToInternet(Context context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }

}
