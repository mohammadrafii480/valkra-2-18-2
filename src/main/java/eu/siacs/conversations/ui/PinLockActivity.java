package eu.siacs.conversations.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import eu.siacs.conversations.R;

public class PinLockActivity extends AppCompatActivity {

    private final StringBuilder pinBuilder = new StringBuilder();
    private static final int MAX_LENGTH = 6;
    private View[] pinBoxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_lock);

        // Ambil referensi 6 kotak pin_box_1 .. pin_box_6
        pinBoxes = new View[]{
                findViewById(R.id.pin_box_1),
                findViewById(R.id.pin_box_2),
                findViewById(R.id.pin_box_3),
                findViewById(R.id.pin_box_4),
                findViewById(R.id.pin_box_5),
                findViewById(R.id.pin_box_6)
        };

        GridLayout keypad = findViewById(R.id.keypad);

        for (int i = 0; i < keypad.getChildCount(); i++) {
            Button btn = (Button) keypad.getChildAt(i);
            String text = btn.getText().toString();

            btn.setOnClickListener(v -> {
                switch (text) {
                    case "C":
                        pinBuilder.setLength(0);
                        break;
                    case "⌫":
                        if (pinBuilder.length() > 0) {
                            pinBuilder.deleteCharAt(pinBuilder.length() - 1);
                        }
                        break;
                    default:
                        if (pinBuilder.length() < MAX_LENGTH) {
                            pinBuilder.append(text);
                        }
                        break;
                }
                updatePinDisplay();

                if (pinBuilder.length() == MAX_LENGTH) {
                    verifyPin();
                }
            });
        }
    }

    private void updatePinDisplay() {
        for (int i = 0; i < MAX_LENGTH; i++) {
            if (i < pinBuilder.length()) {
                pinBoxes[i].setBackgroundResource(R.drawable.pin_filled);  // ⬅ Ganti dengan drawable titik terisi
            } else {
                pinBoxes[i].setBackgroundResource(R.drawable.pin_empty);   // ⬅ Ganti dengan drawable kosong
            }
        }
    }

    private void verifyPin() {
        SharedPreferences prefs = getSharedPreferences("garkom_prefs", MODE_PRIVATE);
        String savedPin = prefs.getString("protected_pin", null);
        String enteredPin = pinBuilder.toString();

        if (savedPin != null && enteredPin.equals(savedPin)) {
            prefs.edit().putBoolean("pin_verified", true).apply();
            finish(); // PIN benar
        } else {
            Toast.makeText(this, "PIN salah", Toast.LENGTH_SHORT).show();
            pinBuilder.setLength(0);
            updatePinDisplay();
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}