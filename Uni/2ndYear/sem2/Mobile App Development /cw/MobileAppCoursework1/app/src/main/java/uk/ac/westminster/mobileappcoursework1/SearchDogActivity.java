package uk.ac.westminster.mobileappcoursework1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.Random;

public class SearchDogActivity extends AppCompatActivity {
    String userBreed;
    EditText userSearch;
    Button submit, stop;
    CountDownTimer myTimer;
    long totalTiming = 360000; // 1hour
    boolean running;
    TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_dog);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userSearch = (EditText) findViewById(R.id.editText_search);

        submit = (Button) findViewById(R.id.submit_btn);

        stop = (Button) findViewById(R.id.stop_btn);



    }
    public void submit(View view) {
        userBreed = userSearch.getText().toString();
        myTimer = new CountDownTimer(totalTiming, 5000) {
            @Override
            public void onTick(long millisUntilFinished) {
                totalTiming = millisUntilFinished;
                Resources res = getResources();
                String[] dogs_array = res.getStringArray(R.array.dogs_array);

                final int imgNumber = randomNumbers(1, 3);
                String searchedBreed = userBreed;
                searchedBreed = searchedBreed.replace(" ", "_");
                searchedBreed = searchedBreed.toLowerCase() + imgNumber;



                ImageView imageView = (ImageView) findViewById(R.id.slideImage_img);
                int breedResorce = getResources().getIdentifier(searchedBreed, "drawable",
                        "uk.ac.westminster.mobileappcoursework1");
                        imageView.setImageResource(breedResorce);


            }
            @Override
            public void onFinish() {

            }
        }.start();
        running = true;

        }


        public int randomNumbers(int amin, int bmax){

        int random = new Random().nextInt((bmax - amin) + 1) + amin;
        return random;
    }
    public void stop(View view){
        Intent intent = new Intent(SearchDogActivity.this, SearchDogActivity.class);
        startActivity(intent);
    }
}
