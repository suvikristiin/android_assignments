package com.example.week12.fragments;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.week12.FightMonstersActivity;
import com.example.week12.GameManager;
import com.example.week12.Monster;
import com.example.week12.Player;
import com.example.week12.R;

public class ShowMonsterFragment extends Fragment {

    private TextView monsterName;
    private TextView monsterLife;
    private Monster monster;


    public ShowMonsterFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_monster, container, false);
        monsterName = view.findViewById(R.id.MonsterNameText);
        monsterLife = view.findViewById(R.id.MonsterLifeText);
        checkBossFightButton();

        monster = GameManager.getInstance().generateMonster();
        int monsterLifeInt = monster.getLife();
        int monsterMaxLife = monster.getMaxLife();
        String monsterLifeString = Integer.toString(monsterLifeInt);
        String maxLifeString = Integer.toString(monsterMaxLife);
        monsterName.setText(monster.getName().toString());
        monsterLife.setText("Elämä: " + monsterLifeString.toString() +"/" + maxLifeString.toString());

        Button attackButton = view.findViewById(R.id.AttackMonsterButton);



        attackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attackMonster();
            }
        });


        return view;
    }

    private void attackMonster() {
        monster.takeDamage(10);

        if (monster.getLife() <= 0) {
            monster = GameManager.getInstance().generateMonster();

        }


        int monsterLifeInt = monster.getLife();
        int monsterMaxLife = monster.getMaxLife();
        String monsterLifeString = Integer.toString(monsterLifeInt);
        String maxLifeString = Integer.toString(monsterMaxLife);
        monsterName.setText(monster.getName().toString());
        monsterLife.setText("Elämä: " + monsterLifeString.toString() +"/" + maxLifeString.toString());

        Player player = GameManager.getInstance().getPlayer();
        player.attack(monster);
        checkBossFightButton();

    }

    private void checkBossFightButton() {

        int playerScore = GameManager.getInstance().getPlayer().getScore();

        if (playerScore >= 100) {
            if (getActivity() instanceof FightMonstersActivity) {
                ((FightMonstersActivity) getActivity()).enableBossFightButton();
            }
        }
    }
}