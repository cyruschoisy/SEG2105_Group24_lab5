package com.example.profilemanager;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    public void SetTeamIcon (View view) {
        Intent returnIntent = new Intent();

        ImageView selectedImage = (ImageView) view;

        returnIntent.putExtra("imageID", selectedImage.getId());
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
