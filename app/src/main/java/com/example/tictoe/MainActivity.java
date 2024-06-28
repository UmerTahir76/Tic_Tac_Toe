package com.example.tictoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private ImageView  refreshBtn;
    private TextView resultTV;
    String b1, b2, b3, b4, b5, b6, b7, b8, b9;
    public int flag = 0;
    public int counter = 0;
    public MediaPlayer mediaPlayer, mediaPlayer2,mediaPlayer3;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.image_btn1);
        btn2 = findViewById(R.id.image_btn2);
        btn3 = findViewById(R.id.image_btn3);
        btn4 = findViewById(R.id.image_btn4);
        btn5 = findViewById(R.id.image_btn5);
        btn6 = findViewById(R.id.image_btn6);
        btn7 = findViewById(R.id.image_btn7);
        btn8 = findViewById(R.id.image_btn8);
        btn9 = findViewById(R.id.image_btn9);
        resultTV = findViewById(R.id.result_text);
        refreshBtn = findViewById(R.id.refresh_btn);


        refreshBtn.setOnClickListener(v -> {
            if (mediaPlayer2 != null) {
                mediaPlayer2.release();
            }
            mediaPlayer2 = MediaPlayer.create(this, R.raw.refresh);
            mediaPlayer2.setOnCompletionListener(mp -> {
                mediaPlayer2.release();
            });
            mediaPlayer2.start();
            btn1.setOnClickListener(this::Check);
            btn2.setOnClickListener(this::Check);
            btn3.setOnClickListener(this::Check);
            btn4.setOnClickListener(this::Check);
            btn5.setOnClickListener(this::Check);
            btn6.setOnClickListener(this::Check);
            btn7.setOnClickListener(this::Check);
            btn8.setOnClickListener(this::Check);
            btn9.setOnClickListener(this::Check);
            btn1.setText("");
            btn2.setText("");
            btn3.setText("");
            btn4.setText("");
            btn5.setText("");
            btn6.setText("");
            btn7.setText("");
            btn8.setText("");
            btn9.setText("");
            counter = 0;
            flag = 0;
            resultTV.setText("");
        });

    }

    public void Check(View v) {
        Button currentBtn = (Button) v;

        if (currentBtn.getText().toString().isEmpty()) {
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
            mediaPlayer = MediaPlayer.create(this,R.raw.button_click);
            mediaPlayer.setOnCompletionListener(mp -> {
                mediaPlayer.release();
            });
            mediaPlayer.start();
            counter++;
            if (flag == 0) {
                currentBtn.setText("X");
                flag = 1;
            } else {
                currentBtn.setText("O");
                flag = 0;
            }
            if (counter > 4) {
                b1 = btn1.getText().toString();
                b2 = btn2.getText().toString();
                b3 = btn3.getText().toString();
                b4 = btn4.getText().toString();
                b5 = btn5.getText().toString();
                b6 = btn6.getText().toString();
                b7 = btn7.getText().toString();
                b8 = btn8.getText().toString();
                b9 = btn9.getText().toString();
                if (mediaPlayer3 != null) {
                    mediaPlayer3.release();
                }
                mediaPlayer3 = MediaPlayer.create(this,R.raw.winner);
                mediaPlayer3.setOnCompletionListener(mp -> {
                    mediaPlayer3.release();
                });
                 if (b1.equals(b2) && b2.equals(b3) && !b1.isEmpty()) {
                    mediaPlayer3.start();
                    resultTV.setText("Winner is "+b1);
                     disableAllButtonsExcept(currentBtn);



                 } else if (b4.equals(b5) && b5.equals(b6) && !b4.isEmpty()) {
                    resultTV.setText("Winner is "+b4);
                     mediaPlayer3.start();
                     disableAllButtonsExcept(currentBtn);


                 } else if (b7.equals(b8) && b8.equals(b9) && !b7.isEmpty()) {
                    resultTV.setText("Winner is "+b7);
                     mediaPlayer3.start();
                     disableAllButtonsExcept(currentBtn);

                } else if (b1.equals(b4) && b4.equals(b7) && !b1.isEmpty()) {
                    resultTV.setText("Winner is "+b1);
                     mediaPlayer3.start();
                     disableAllButtonsExcept(currentBtn);

                } else if (b2.equals(b5) && b5.equals(b8) && !b2.isEmpty()) {
                    resultTV.setText("Winner is "+b2);
                     mediaPlayer3.start();
                     disableAllButtonsExcept(currentBtn);

                } else if (b3.equals(b6) && b6.equals(b9) && !b3.isEmpty()) {
                    resultTV.setText("Winner is "+b3);
                     mediaPlayer3.start();
                     disableAllButtonsExcept(currentBtn);

                } else if (b1.equals(b5) && b5.equals(b9) && !b1.isEmpty()) {
                    resultTV.setText("Winner is "+b1);
                     mediaPlayer3.start();
                     disableAllButtonsExcept(currentBtn);


                } else if (b3.equals(b5) && b5.equals(b7) && !b3.isEmpty()) {
                    resultTV.setText("Winner is "+b3);
                     mediaPlayer3.start();
                     disableAllButtonsExcept(currentBtn);


                } else if(counter == 9){
                     resultTV.setText("Draw ");

                }
            }
        } else {
            Toast.makeText(this, "Cant change !!!", Toast.LENGTH_SHORT).show();
        }

    }
    private void disableAllButtonsExcept(Button currentBtn) {
        btn1.setOnClickListener(null);
        btn2.setOnClickListener(null);
        btn3.setOnClickListener(null);
        btn4.setOnClickListener(null);
        btn5.setOnClickListener(null);
        btn6.setOnClickListener(null);
        btn7.setOnClickListener(null);
        btn8.setOnClickListener(null);
        btn9.setOnClickListener(null);
    }
}