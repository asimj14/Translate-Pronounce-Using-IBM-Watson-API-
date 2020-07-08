package uk.ac.westminster.mobileappcoursework1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    //Function to Identify the dog

    public void Identify_dog(View view) {
        Intent intent = new Intent(MainActivity.this, IdentifyDog.class);
        startActivity(intent);

    }
    //Function to Identify the breed

    public void Identify_breed(View view) {
        Intent intent = new Intent(MainActivity.this, IdentifyBreed.class);
        startActivity(intent);

    }
    //Function to search the breed

    public void search_breed(View view) {
        Intent intent = new Intent(MainActivity.this, SearchDogActivity.class);
        startActivity(intent);

    }

    //Exit Button Function
    public void exit(View view) {
        AlertDialog.Builder exitBuilder = new AlertDialog.Builder(this);

        exitBuilder.setTitle("Exit ?");
        exitBuilder.setMessage("Are you sure you want to Exit ?");
        exitBuilder.setCancelable(false);
        exitBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.finish();
            }
        });
        exitBuilder.setNegativeButton("No", null)
                .show();

    }
}
