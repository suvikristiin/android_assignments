package com.example.week12.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.week12.GameManager;
import com.example.week12.Monster;
import com.example.week12.Player;
import com.example.week12.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BossFightFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BossFightFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private boolean halfLifeRestored = false;
    Monster bossMonster;
    TextView bosstext;

    public BossFightFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BossFightFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BossFightFragment newInstance(String param1, String param2) {
        BossFightFragment fragment = new BossFightFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_boss_fight, container, false);

        bosstext = view.findViewById(R.id.BossText);
        Button attackBossButton = view.findViewById(R.id.AttackBossButton);
        ImageView bossImage = view.findViewById(R.id.BossImage);


        bossMonster = new Monster(200, "Boss Monster") {
            @Override
            public void takeDamage(int damage) {

                int newLife = Math.max(0, getLife() - damage);


                if (newLife < getMaxLife() / 2 && !halfLifeRestored) {

                    newLife = getMaxLife();
                    halfLifeRestored = true;
                }


                setLife(newLife);
            }

        };


        int monsterLifeInt = bossMonster.getLife();
        int monsterMaxLife = bossMonster.getMaxLife();
        String monsterLifeString = Integer.toString(monsterLifeInt);
        String maxLifeString = Integer.toString(monsterMaxLife);

        bosstext.setText(bossMonster.getName().toString() + " " +  monsterLifeString.toString() +"/" + maxLifeString.toString());


        attackBossButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attackBoss();
            }
        });

        return view;
    }

    private void attackBoss() {
        bossMonster.takeDamage(50);

        int monsterLifeInt = bossMonster.getLife();
        int monsterMaxLife = bossMonster.getMaxLife();
        String monsterLifeString = Integer.toString(monsterLifeInt);
        String maxLifeString = Integer.toString(monsterMaxLife);
        bosstext.setText(bossMonster.getName().toString() + " " +  monsterLifeString.toString() +"/" + maxLifeString.toString());


        Player player = GameManager.getInstance().getPlayer();
        player.attack(bossMonster);
    }
}