package uk.ac.westminster.mobileappcoursework1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class IdentifyBreed extends AppCompatActivity {
    Spinner mySpinner;
    String savePickedName;
    TextView result;
    TextView cResult;
    Button submit, next;
    ImageView myImage;
    int dogResource;
    Boolean randomState = true;
    Boolean textVisible1 = true;
    Boolean textVisible2 = true;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("dogResource",dogResource);
        outState.putBoolean("randomState",randomState);
        outState.putBoolean("textVisible1",true);
        outState.putBoolean("textVisible2",true);



        if(result.getVisibility()== View.VISIBLE){
            outState.putBoolean("textVisible1",textVisible1);


        }
        if(cResult.getVisibility()== View.VISIBLE){
            outState.putBoolean("textVisible2",textVisible2);


        }

        Log.d("*** onSaveState ***","is called");

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_breed);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        result = (TextView) findViewById(R.id.correct_txt);
        cResult = (TextView) findViewById(R.id.correctanswer_txt);

        if(savedInstanceState != null){



            dogResource = savedInstanceState.getInt("dogResource");
            textVisible1 = savedInstanceState.getBoolean("textVisible1");
            textVisible2 = savedInstanceState.getBoolean("textVisible2");

            if(textVisible1){
                result.setVisibility(View.VISIBLE);

            }
            if(textVisible2){
                cResult.setVisibility(View.VISIBLE);

            }


            randomState = false;
        }

        mySpinner = findViewById(R.id.spinner_dogs_breeds_names);

        ArrayAdapter<String> breedsAdapter = new ArrayAdapter<String>(IdentifyBreed.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.dogs_array));

        breedsAdapter.setDropDownViewResource(android .R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(breedsAdapter);

        if(randomState == true) {
            Resources res = getResources();
            String[] dogs_array = res.getStringArray(R.array.dogs_array);
            //generate random number to pick a random breed
            int randomNumber = randomNumbers(0, 9);

            //pick random dog name from array
            String randomDogName = dogs_array[randomNumber];
            savePickedName = randomDogName;

            result = (TextView) findViewById(R.id.correct_txt);
            result.setText("Correct : " + savePickedName);

            randomDogName = randomDogName.replace(" ", "_");


            int imgNumber = randomNumbers(1, 5);
            randomDogName = randomDogName.toLowerCase() + imgNumber;


            //for example it will return husky2
            dogResource = getResources().getIdentifier(randomDogName, "drawable",
                    "uk.ac.westminster.mobileappcoursework1");
        }

            //set image view
            myImage = (ImageView) findViewById(R.id.dogImageView);
            myImage.setImageResource(dogResource);



    }
    public int randomNumbers(int amin, int bmax){

        int random = new Random().nextInt((bmax - amin) + 1) + amin;
        return random;
    }
    //function to submit the answer
    public void submit(View view){

        submit = (Button) findViewById(R.id.submit_btn);
        next = (Button) findViewById(R.id.next_btn);



        if((mySpinner.getSelectedItem().toString().equals(savePickedName)))
        {
            result.setText("CORRECT BREED!!!");
            result.setTextColor(Color.parseColor("#013220"));
            result.setTextSize(14);
            result.setVisibility(view.VISIBLE);

        }

        if (!(mySpinner.getSelectedItem().toString().equals((savePickedName)))) {

            result.setText("Wrong breed !!! \n");
            result.setTextColor(Color.RED);
            result.setTextSize(14);
            cResult = (TextView) findViewById(R.id.correctanswer_txt);
            cResult.setText("Correct breed: "+savePickedName);
            cResult.setTextColor(Color.BLUE);
            cResult.setTextSize(14);
            result.setVisibility(view.VISIBLE);
            cResult.setVisibility(view.VISIBLE);


        }
        submit.setVisibility(View.INVISIBLE);
        next.setVisibility(View.VISIBLE);


    }
    public void next(View view){
        Intent intent = new Intent(IdentifyBreed.this, IdentifyBreed.class);
        startActivity(intent);


    }

}
