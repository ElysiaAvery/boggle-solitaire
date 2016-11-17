package com.example.guest.bogglesolitaire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.rollDice) Button mRollDice;
    @Bind(R.id.editText) EditText mEditText;
    @Bind(R.id.wordButton) Button mWordButton;
    @Bind(R.id.letterList) ListView mLetterList;
    public static final String TAG = PlayActivity.class.getSimpleName();
    private final String [] consonants = new String [] {"B", "C", "D", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z"};
    private final String [] vowels = new String [] {"A", "E", "I", "O", "U"};
    String [] randomLetters = new String[8];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        ButterKnife.bind(this);

        mRollDice.setOnClickListener(this);
        mWordButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ButterKnife.bind(this);
        String [] letters = new String[8];
        if(v == mRollDice) {
            int keepOn = letters.length;
            for (int i = 0; i < 2; i++) {
                int randomNum = (int) (Math.random()*100);
                letters[i] = vowels[randomNum%vowels.length];
            }
            for (int i = 2; i < 8; i++) {
                int randomNum = (int) (Math.random()*100);
                letters[i] = consonants[randomNum%consonants.length];
            }
            while(keepOn > 0) {
                int randomNum = (int) (Math.random()*100);
                randomNum = randomNum % letters.length;
                if(!letters[randomNum].equals("")) {
                    randomLetters[8-keepOn] = letters[randomNum];
                    letters[randomNum] = "";
                    keepOn = keepOn - 1;
                }
            }
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, randomLetters);
            mLetterList.setAdapter(adapter);
        }
        else if(v == mWordButton) {
            boolean wordCheck = true;
            boolean badWord = true;
            String userText = mEditText.getText().toString();
            for(int i = 0; i < userText.length(); i++) {
                if(!Arrays.asList(randomLetters).contains(Character.toString(userText.charAt(i)))) {
                    badWord = false;
                }
            } if (userText.length() < 3) {
                wordCheck = false;
            } if (wordCheck == false) {
                Toast.makeText(PlayActivity.this, "Your answer needs to be at least 3 characters long!", Toast.LENGTH_SHORT).show();
                return;
            } if (badWord == false) {
                Toast.makeText(PlayActivity.this, "Your answer needs to only include the letters we gave you!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(wordCheck && badWord) {
                Intent intent = new Intent(PlayActivity.this, WinActivity.class);
                startActivity(intent);
            }
        }
    }
}
