package id.hw.labs.movieupdate;


import android.app.Application;

/**
 * Created by HWAHYUDI on 26/08/2017.
 */

public class AppController extends Application {
    private static AppController controller;

    public static synchronized AppController getInstance() {
        return controller;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        controller = this;
    }
}
