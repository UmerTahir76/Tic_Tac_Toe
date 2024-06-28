package com.example.tictoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class newMain extends AppCompatActivity {
    private ImageButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    public int currentPlayer = 0; // 0 for player X, 1 for player O
    public int counter = 0;
    private int imageWidth;
    private int imageHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get desired image width and height (adjust as needed)
        imageWidth = getResources().getDimensionPixelSize(R.dimen.image_width);
        imageHeight = getResources().getDimensionPixelSize(R.dimen.image_height);

        btn1 = findViewById(R.id.image_btn1);
        btn2 = findViewById(R.id.image_btn2);
        btn3 = findViewById(R.id.image_btn3);
        btn4 = findViewById(R.id.image_btn4);
        btn5 = findViewById(R.id.image_btn5);
        btn6 = findViewById(R.id.image_btn6);
        btn7 = findViewById(R.id.image_btn7);
        btn8 = findViewById(R.id.image_btn8);
        btn9 = findViewById(R.id.image_btn9);
    }

    public void Check(View v) {
        ImageButton currentBtn = (ImageButton) v;

        if (currentBtn.getDrawable() == null) {
            currentPlayer = counter % 2; // Alternating between players
            int imageResource = currentPlayer == 0 ? R.drawable.x_mark : R.drawable.tick_mark;
            Bitmap resizedImage = resizeImage(imageResource, imageWidth, imageHeight);
            currentBtn.setImageBitmap(resizedImage);
            counter++;

            if (counter >= 5) { // Check for winner or draw after 5 moves
                if (checkWinner()) {
                    String winner = currentPlayer == 0 ? "X" : "O";
                    Toast.makeText(this, "Winner: " + winner, Toast.LENGTH_SHORT).show();
                    restart();
                    return; // Exit the method after restarting
                } else if (counter == 9) {
                    Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
                    restart();
                    return; // Exit the method after restarting
                }
            }
        } else {
            Toast.makeText(this, "Cannot change!", Toast.LENGTH_SHORT).show();
        }
    }
    private Bitmap resizeImage(int imageResource, int width, int height) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageResource);
        return Bitmap.createScaledBitmap(bitmap, width, height, false);
    }

    private boolean checkWinner() {
        // Check rows
        if (checkLine(btn1, btn2, btn3) || checkLine(btn4, btn5, btn6) || checkLine(btn7, btn8, btn9)) {
            return true;
        }
        // Check columns
        if (checkLine(btn1, btn4, btn7) || checkLine(btn2, btn5, btn8) || checkLine(btn3, btn6, btn9)) {
            return true;
        }
        // Check diagonals
        if (checkLine(btn1, btn5, btn9) || checkLine(btn3, btn5, btn7)) {
            return true;
        }
        return false;
    }

    private boolean checkLine(ImageButton btn1, ImageButton btn2, ImageButton btn3) {
        // Check if any of the buttons' drawable is null
        if (btn1.getDrawable() == null || btn2.getDrawable() == null || btn3.getDrawable() == null) {
            return false;
        }

        // Check if the drawables are equal
        if (btn1.getDrawable().getConstantState() != null &&
                btn1.getDrawable().getConstantState().equals(btn2.getDrawable().getConstantState()) &&
                btn2.getDrawable().getConstantState().equals(btn3.getDrawable().getConstantState())) {
            return true;
        }

        return false;
    }


    public void restart() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            btn1.setImageBitmap(null);
            btn2.setImageBitmap(null);
            btn3.setImageBitmap(null);
            btn4.setImageBitmap(null);
            btn5.setImageBitmap(null);
            btn6.setImageBitmap(null);
            btn7.setImageBitmap(null);
            btn8.setImageBitmap(null);
            btn9.setImageBitmap(null);
            counter = 0;
        }, 2000);
    }
}
