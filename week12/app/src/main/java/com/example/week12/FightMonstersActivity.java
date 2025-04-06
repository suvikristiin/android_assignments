package com.example.week12;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.week12.MainActivity;
import com.example.week12.R;
import com.example.week12.fragments.ShowMonsterFragment;
import com.example.week12.fragments.BossFightFragment;

public class FightMonstersActivity extends AppCompatActivity {
    Button bossFightFragmentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fight_monsters);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button showMonsterFragmentButton = findViewById(R.id.ShowMonsterFragmentButton);
        showMonsterFragmentButton.setOnClickListener(listener);

        Player player = GameManager.getInstance().getPlayer();
        bossFightFragmentButton = findViewById(R.id.BossFightFragmentButton);
        bossFightFragmentButton.setOnClickListener(listener2);

        int playerScore = GameManager.getInstance().getPlayer().getScore();

        if (playerScore >= 100) {
            enableBossFightButton();
        }

    }

    public void enableBossFightButton() {
        bossFightFragmentButton.setEnabled(true);

    }

    public void moveToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Fragment fragment = null;

            if (view.getId() == R.id.ShowMonsterFragmentButton) {
                fragment = new ShowMonsterFragment();
            }


            if (fragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.FightMonstersFrame, fragment)
                        .addToBackStack(null)
                        .commit();
            }

        }
    };

    private View.OnClickListener listener2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Fragment fragment = null;

            if (view.getId() == R.id.BossFightFragmentButton) {
                fragment = new BossFightFragment();
            }


            if (fragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.FightMonstersFrame, fragment)
                        .addToBackStack(null)
                        .commit();
            }

        }
    };


}