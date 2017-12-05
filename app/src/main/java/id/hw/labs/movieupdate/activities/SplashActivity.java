package id.hw.labs.movieupdate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private static String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent goToMainActivity = new Intent(SplashActivity.this, MainActivity.class);
        finish();
        startActivity(goToMainActivity);
    }
}
