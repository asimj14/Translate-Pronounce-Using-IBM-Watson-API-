package uk.ac.westminster.mobileappcoursework1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class IdentifyDog extends AppCompatActivity {
    String savePickedBreed;
    TextView breed;
    Button next;
    ImageView myImage1,myimage2,myimage3;
    int rand1,rand2,rand3;
    int dogResource1,dogResource2,dogResource3;
    String [] threeDogBreeds = new String[3];
    TextView result2;
    Boolean randomState = true;
    Boolean textVisible1 = true;



    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("dogResource1",dogResource1);
        outState.putInt("dogResource2",dogResource2);
        outState.putInt("dogResource3",dogResource3);
        outState.putBoolean("randomState",randomState);
        outState.putBoolean("textVisible1",true);

        outState.putString("savePickedBreed",savePickedBreed);
        if(result2.getVisibility()== View.VISIBLE){
            outState.putBoolean("textVisible1",textVisible1);


        }



        Log.d("*** onSaveState ***","is called");

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_dog);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        result2 = (TextView) findViewById(R.id.result2_txt);

        if (savedInstanceState != null) {
            dogResource1 = savedInstanceState.getInt("dogResource1");
            dogResource2 = savedInstanceState.getInt("dogResource2");
            dogResource3 = savedInstanceState.getInt("dogResource3");
            savePickedBreed = savedInstanceState.getString("savePickedBreed");
            randomState = false;
            textVisible1 = savedInstanceState.getBoolean("textVisible1");
            if(textVisible1){
                result2.setVisibility(View.VISIBLE);

            }
        }

        if (randomState == true) {


            rand1 = randomNumbers(0, 9);
            rand2 = randomNumbers(0, 9);
            if (rand2 == rand1) {
                rand2 = randomNumbers(0, 9);


            }

            rand3 = randomNumbers(0, 9);
            if (rand3 == rand1) {
                rand3 = randomNumbers(0, 9);
            }
            if (rand3 == rand2) {
                rand3 = randomNumbers(0, 9);
            }

            Resources res = getResources();
            String[] dogs_array = res.getStringArray(R.array.dogs_array);

            //image 1
            String randomDogName1 = dogs_array[rand1];
            threeDogBreeds[0] = randomDogName1;
            //set tag to be then compared
            myImage1 = (ImageView) findViewById(R.id.first_image);

            myImage1.setTag(randomDogName1);
            randomDogName1 = randomDogName1.replace(" ", "_");


            //generate image number for the resource
            int imgNumber1 = randomNumbers(1, 5);
            randomDogName1 = randomDogName1.toLowerCase() + imgNumber1;
            dogResource1 = getResources().getIdentifier(randomDogName1, "drawable",
                    "uk.ac.westminster.mobileappcoursework1");


            //image 2

            String randomDogName2 = dogs_array[rand2];
            threeDogBreeds[1] = randomDogName2;
            myimage2 = (ImageView) findViewById(R.id.second_image);

            myimage2.setTag(randomDogName2);
            randomDogName2 = randomDogName2.replace(" ", "_");


            int imgNumber2 = randomNumbers(1, 5);
            randomDogName2 = randomDogName2.toLowerCase() + imgNumber2;
            randomDogName2 = randomDogName2.replace(" ", "_");

            dogResource2 = getResources().getIdentifier(randomDogName2, "drawable",
                    "uk.ac.westminster.mobileappcoursework1");


            //image 3


            String randomDogName3 = dogs_array[rand3];
            threeDogBreeds[2] = randomDogName3;
            myimage3 = (ImageView) findViewById(R.id.third_image);

            myimage3.setTag(randomDogName3);
            int imgNumber3 = randomNumbers(1, 5);
            randomDogName3 = randomDogName3.toLowerCase() + imgNumber3;
            randomDogName3 = randomDogName3.replace(" ", "_");


            dogResource3 = getResources().getIdentifier(randomDogName3, "drawable",
                    "uk.ac.westminster.mobileappcoursework1");
            //generate random number to pick a random breed from those saved in array
            int randomNumber = randomNumbers(0,2);
            //pick random dog name from array
            String randomDogName = threeDogBreeds[randomNumber];
            savePickedBreed = randomDogName;

            breed = (TextView) findViewById(R.id.breed_txt);
            breed.setText(savePickedBreed);
            breed.setTextColor(Color.parseColor("#5B4C49"));
            breed.setTextSize(18);

        }
        myImage1 = (ImageView) findViewById(R.id.first_image);
        myimage2 = (ImageView) findViewById(R.id.second_image);
        myimage3 = (ImageView) findViewById(R.id.third_image);



        myImage1.setImageResource(dogResource1);
        myimage2.setImageResource(dogResource2);
        myimage3.setImageResource(dogResource3);


        breed = (TextView) findViewById(R.id.breed_txt);
        breed.setText(savePickedBreed);
        breed.setTextColor(Color.parseColor("#5B4C49"));
        breed.setTextSize(18);



    }
    public void onImageClick1(View view){
        result2 = (TextView) findViewById(R.id.result2_txt);

         if((myImage1.getTag().toString().equals(savePickedBreed))){

            result2.setText("CORRECT BREED!!!");
            result2.setTextColor(Color.parseColor("#013220"));
            result2.setTextSize(14);
            result2.setVisibility(view.VISIBLE);
            myimage2.setClickable(false);
            myimage3.setClickable(false);


         }
        if(!(myImage1.getTag().toString().equals(savePickedBreed))){

            result2.setText("WRONG BREED!!! \n");
            result2.setTextColor(Color.RED);
            result2.setTextSize(14);
            result2.setVisibility(view.VISIBLE);
            myimage2.setClickable(false);
            myimage3.setClickable(false);

        }
    }
    public void onImageClick2(View view){
        result2 = (TextView) findViewById(R.id.result2_txt);


        if((myimage2.getTag().toString().equals(savePickedBreed))){
            result2.setText("CORRECT BREED!!!");
            result2.setTextColor(Color.parseColor("#013220"));
            result2.setTextSize(14);
            result2.setVisibility(view.VISIBLE);
            myImage1.setClickable(false);
            myimage3.setClickable(false);

        }
        if(!(myimage2.getTag().toString().equals(savePickedBreed))){

            result2.setText("WRONG BREED!!! \n");
            result2.setTextColor(Color.RED);
            result2.setTextSize(14);
            result2.setVisibility(view.VISIBLE);
            myImage1.setClickable(false);
            myimage3.setClickable(false);

        }


    }
    public void onImageClick3(View view){
        result2 = (TextView) findViewById(R.id.result2_txt);


        if((myimage3.getTag().toString().equals(savePickedBreed))){
            result2.setText("CORRECT BREED!!!");
            result2.setTextColor(Color.parseColor("#013220"));
            result2.setTextSize(14);
            result2.setVisibility(view.VISIBLE);
            myImage1.setClickable(false);
            myimage2.setClickable(false);




        }
        if(!(myimage3.getTag().toString().equals(savePickedBreed))){

            result2.setText("WRONG BREED!!! \n");
            result2.setTextColor(Color.RED);
            result2.setTextSize(14);
            result2.setVisibility(view.VISIBLE);
            myImage1.setClickable(false);
            myimage2.setClickable(false);

        }


    }
    public void next(View view){
        Intent intent = new Intent(IdentifyDog.this, IdentifyDog.class);
        startActivity(intent);


    }

    public int randomNumbers(int amin, int bmax){

        int random = new Random().nextInt((bmax - amin) + 1) + amin;
        return random;
    }
}
