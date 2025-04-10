package com.example.gamexowithandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class PlayingFieldEasy extends AppCompatActivity {

    private final List<int[]> combinationlist = new ArrayList<>();

    private int activePlayer = 1;
    private int[] boxPositions = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int totalSelectBoxes = 1;

    private ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;

    private TextView playerName;
    private TextView androidName;
    //private String androidName="Android";

    private int currentScoreOne = 0;
    private int currentScoreAndroid = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_playing_field_easy);

        combinationlist.add(new int[]{0, 1, 2});
        combinationlist.add(new int[]{3, 4, 5});
        combinationlist.add(new int[]{6, 7, 8});
        combinationlist.add(new int[]{0, 3, 6});
        combinationlist.add(new int[]{1, 4, 7});
        combinationlist.add(new int[]{2, 5, 8});
        combinationlist.add(new int[]{0, 4, 8});
        combinationlist.add(new int[]{2, 4, 6});


        playerName = findViewById(R.id.playerName);
        androidName = findViewById(R.id.androidName);

        String getPlayerName = getIntent().getStringExtra("Player");
        String getAndroidName = getIntent().getStringExtra("Android");

        playerName.setText(getPlayerName);
        androidName.setText(getAndroidName);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);
        image9 = findViewById(R.id.image9);

        changePlayerTurn(activePlayer);


        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectTable(0)) {
                    performAction((ImageView) v, 0);
                }
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectTable(1)) {
                    performAction((ImageView) v, 1);
                }
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectTable(2)) {
                    performAction((ImageView) v, 2);
                }
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectTable(3)) {
                    performAction((ImageView) v, 3);
                }
            }
        });
        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectTable(4)) {
                    performAction((ImageView) v, 4);
                }
            }
        });
        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectTable(5)) {
                    performAction((ImageView) v, 5);
                }
            }
        });
        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectTable(6)) {
                    performAction((ImageView) v, 6);
                }
            }
        });
        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectTable(7)) {
                    performAction((ImageView) v, 7);
                }
            }
        });
        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectTable(8)) {
                    performAction((ImageView) v, 8);
                }
            }
        });
    }

    private void changePlayerTurn(int currentPlayerTurn) {
        activePlayer = currentPlayerTurn;

        LinearLayout playerLayoutOuter = findViewById(R.id.playerLayoutOuter);
        LinearLayout androidLayoutOuter = findViewById(R.id.androidLayoutOuter);

        if (activePlayer == 1) {
            playerLayoutOuter.setBackgroundResource(R.drawable.black_border);
            androidLayoutOuter.setBackgroundResource(R.drawable.white_box);
        } else {
            androidLayoutOuter.setBackgroundResource(R.drawable.black_border);
            playerLayoutOuter.setBackgroundResource(R.drawable.white_box);
        }
    }

    private void performAction(ImageView imageView, int selectedBoxPosition) {
        boxPositions[selectedBoxPosition] = activePlayer;
        imageView.setBackgroundResource(R.drawable.white_box);
        imageView.setScaleType(ImageView.ScaleType.CENTER);

        playerName = findViewById(R.id.playerName);
        androidName = findViewById(R.id.androidName);

        TextView scorePlayer = findViewById(R.id.scoreOne);
        TextView scoreAndroid = findViewById(R.id.scoreAndroid);

        if (activePlayer == 1) {
            imageView.setImageResource(R.drawable.xim);

            if (checkResults()) {
                ResultDialog resultDialog = new ResultDialog(PlayingFieldEasy.this, playerName.getText().toString() + " is winner", PlayingFieldEasy.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
                currentScoreOne++;
                scorePlayer.setText(String.valueOf(currentScoreOne));
            } else if (totalSelectBoxes ==9) {
                ResultDialog resultDialog = new ResultDialog(PlayingFieldEasy.this, " Math Draw", PlayingFieldEasy.this);
                resultDialog.setCancelable(false);
                resultDialog.show();

            } else {
                changePlayerTurn(2);
                                totalSelectBoxes++;
                                
            }
        } else {
            imageView.setImageResource(R.drawable.oim);
            if (checkResults()) {
                ResultDialog resultDialog = new ResultDialog(PlayingFieldEasy.this, androidName.getText().toString() + " is winner", PlayingFieldEasy.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
                currentScoreAndroid++;
                scoreAndroid.setText(String.valueOf(currentScoreAndroid));
            }
            else if (totalSelectBoxes ==9) {
                ResultDialog resultDialog = new ResultDialog(PlayingFieldEasy.this, " Math Draw", PlayingFieldEasy.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            }else {
                changePlayerTurn(1);
                totalSelectBoxes++;
            }
        }
    }


    private boolean checkResults() {
        boolean response = false;
        for (int i = 0; i < combinationlist.size(); i++) {
            final int[] combination = combinationlist.get(i);

            if (boxPositions[combination[0]] == activePlayer && boxPositions[combination[1]] == activePlayer && boxPositions[combination[2]] == activePlayer) {
                response = true;
            }
        }
        return response;
    }

    private boolean isBoxSelectTable(int boxPosition) {
        boolean response = false;
        if (boxPositions[boxPosition] == 0) {
            response = true;
        }
        return response;
    }

    public void restartMatch() {
        boxPositions = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        activePlayer = 1;
        totalSelectBoxes = 1;
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);
        image9 = findViewById(R.id.image9);

        image1.setImageResource(R.drawable.white_box);
        image2.setImageResource(R.drawable.white_box);
        image3.setImageResource(R.drawable.white_box);
        image4.setImageResource(R.drawable.white_box);
        image5.setImageResource(R.drawable.white_box);
        image6.setImageResource(R.drawable.white_box);
        image7.setImageResource(R.drawable.white_box);
        image8.setImageResource(R.drawable.white_box);
        image9.setImageResource(R.drawable.white_box);
    }

}