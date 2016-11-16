package com.example.guest.bogglesolitaire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.playGame) Button mPlayGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPlayGame.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mPlayGame) {
            Intent intent = new Intent(MainActivity.this, PlayActivity.class);
            startActivity(intent);
        }
    }
}
