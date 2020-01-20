package com.example.jay.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.time.temporal.TemporalAccessor;

public class MainActivity extends AppCompatActivity {
    int teamAScore=0,teamBScore=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForTeamA(teamAScore);
        displayForTeamB(teamBScore);
    }

    public void add2ForTeamA(View v){
        teamAScore+=2;
        displayForTeamA(teamAScore);
    }

    public void add3ForTeamA(View v){
        teamAScore+=3;
        displayForTeamA(teamAScore);
    }

    public void add1ForTeamA(View v){
        teamAScore++;
        displayForTeamA(teamAScore);
    }

    public void add2ForTeamB(View v){
        teamBScore+=2;
        displayForTeamB(teamBScore);
    }

    public void add3ForTeamB(View v){
        teamBScore+=3;
        displayForTeamB(teamBScore);
    }

    public void add1ForTeamB(View v){
        teamBScore++;
        displayForTeamB(teamBScore);
    }

    public void reset(View v){
        teamAScore=teamBScore=0;
        displayForTeamA(teamAScore);
        displayForTeamB(teamBScore);
        displayLeadingTeam();
    }

    public void displayForTeamA(int score){
        TextView scoreView = (TextView)findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(teamAScore));
        displayLeadingTeam();
    }

    public void displayForTeamB(int score){
        TextView scoreView = (TextView)findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(teamBScore));
        displayLeadingTeam();
    }

    public void displayLeadingTeam(){
        TextView leader = (TextView)findViewById(R.id.leading_team);
        String leadingTeamName="None";
        if(teamBScore>teamAScore)
            leadingTeamName="Team B";
        else if(teamAScore>teamBScore)
            leadingTeamName="Team A";
        else
            leadingTeamName="None";

        leader.setText(leadingTeamName);
    }


}
