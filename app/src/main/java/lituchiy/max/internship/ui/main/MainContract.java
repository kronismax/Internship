package lituchiy.max.internship.ui.main;


public interface MainContract {

    interface View {

        void showProgressBar(int status);

        void showAppeal();

    }

    interface UserActionListener {

        void loadAppealList(String query, int page, int offset);

    }

}
