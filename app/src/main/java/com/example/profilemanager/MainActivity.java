package com.example.profilemanager;

import static com.example.profilemanager.R.*;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void OnOpenInGoogleMaps (View view) {
        EditText teamAddress = (EditText) findViewById(R.id.teamAddress);

        Uri gmmIntentUri = Uri.parse("http://maps.google.co.in/maps?q=" + teamAddress.getText());

        Intent mapIntent = new Intent (Intent.ACTION_VIEW, gmmIntentUri);

        mapIntent.setPackage("com.google.android.apps.maps");

        startActivity(mapIntent);
    }

    ActivityResultLauncher<Intent> profileActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @SuppressLint("NonConstantResourceId")
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        ImageView avatarImage = (ImageView) findViewById(R.id.avatarImage);

                        String drawableName = "base";
                        switch (data.getIntExtra("imageViewID", R.id.flag1)) {
                            case R.id.flag1:
                                drawableName = "flag_ca";
                                break;
                            case R.id.flag2:
                                drawableName = "flag_eg";
                                break;
                            case R.id.flag3:
                                drawableName = "flag_fr";
                                break;
                            case R.id.flag4:
                                drawableName = "flag_jp";
                                break;
                            case R.id.flag5:
                                drawableName = "flag_kr";
                                break;
                            case R.id.flag6:
                                drawableName = "flag_sp";
                                break;
                            case R.id.flag7:
                                drawableName = "flag_tr";
                                break;
                            case R.id.flag8:
                                drawableName = "flag_uk";
                                break;
                            case R.id.flag9:
                                drawableName = "flag_us";
                                break;

                        }
                        int resID = getResources().getIdentifier(drawableName, "drawable", getPackageName());
                        avatarImage.setImageResource(resID);
                    }
                }
            }
    );

    public void OnSetAvatarButton (View view) {
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        profileActivityResultLauncher.launch(intent);
    }
}