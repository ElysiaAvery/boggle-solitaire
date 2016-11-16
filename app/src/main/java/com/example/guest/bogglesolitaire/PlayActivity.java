package com.example.guest.bogglesolitaire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.textView1) TextView mTextView1;
    @Bind(R.id.textView2) TextView mTextView2;
    @Bind(R.id.textView3) TextView mTextView3;
    @Bind(R.id.textView4) TextView mTextView4;
    @Bind(R.id.textView5) TextView mTextView5;
    @Bind(R.id.textView6) TextView mTextView6;
    @Bind(R.id.textView7) TextView mTextView7;
    @Bind(R.id.textView8) TextView mTextView8;
    @Bind(R.id.rollDice) Button mRollDice;
    @Bind(R.id.editText) EditText mEditText;
    @Bind(R.id.wordButton) Button mWordButton;
    public static final String TAG = PlayActivity.class.getSimpleName();
    private final String [] consonants = new String [] {"B", "C", "D", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z"};
    private final String [] vowels = new String [] {"A", "E", "I", "O", "U"};


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
        if(v == mRollDice) {
            String [] letters = new String[8];
            List<String> randomLetters = new ArrayList<String>();
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
                int randomNum = (int) (Math.random()*100)%keepOn;
                if(!letters[randomNum].equals("")) {
                    randomLetters.add(letters[randomNum]);
                    letters[randomNum] = "";
                    keepOn--;
                }
            }
            Log.d(TAG, randomLetters.get(0));
            Log.d(TAG, randomLetters.get(7));
        }
        else if(v == mWordButton) {

        }
    }
}
