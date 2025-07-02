package eu.siacs.conversations.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import eu.siacs.conversations.R;

public class SetPinActivity extends AppCompatActivity {

    private final int MAX_PIN_LENGTH = 6;
    private final StringBuilder pinBuilder = new StringBuilder();
    private View[] pinBoxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pin);

        // Inisialisasi 6 kotak PIN
        pinBoxes = new View[]{
                findViewById(R.id.pin_box_1),
                findViewById(R.id.pin_box_2),
                findViewById(R.id.pin_box_3),
                findViewById(R.id.pin_box_4),
                findViewById(R.id.pin_box_5),
                findViewById(R.id.pin_box_6)
        };

        // Keypad Grid
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
                        if (pinBuilder.length() < MAX_PIN_LENGTH) {
                            pinBuilder.append(text);
                        }
                        break;
                }
                updatePinDisplay();
            });
        }

        // Tombol simpan
        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(v -> {
            if (pinBuilder.length() != MAX_PIN_LENGTH) {
                Toast.makeText(this, "PIN harus 6 digit", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences prefs = getSharedPreferences("garkom_prefs", MODE_PRIVATE);
            prefs.edit().putString("protected_pin", pinBuilder.toString()).apply();

            Toast.makeText(this, "PIN berhasil disimpan", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void updatePinDisplay() {
        for (int i = 0; i < pinBoxes.length; i++) {
            if (i < pinBuilder.length()) {
                pinBoxes[i].setBackgroundResource(R.drawable.pin_box_filled);
            } else {
                pinBoxes[i].setBackgroundResource(R.drawable.pin_box_bg);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (pinBuilder.length() == 0) {
            super.onBackPressed();
        } else {
            pinBuilder.setLength(0);
            updatePinDisplay();
        }
    }
}