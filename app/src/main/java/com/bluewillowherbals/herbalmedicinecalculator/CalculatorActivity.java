package com.bluewillowherbals.herbalmedicinecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.util.HashMap;

public class CalculatorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle(R.string.title);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }

    public static HashMap solveForVolumeParts(double desiredVolume, double desiredPercentAlcohol, double percentAlcoholSubstanceOne, double percentAlcoholSubstanceTwo) {
        HashMap calculationOutput = new HashMap();
        double substanceVolumeOne = (desiredVolume * desiredPercentAlcohol - desiredVolume * percentAlcoholSubstanceTwo)/(percentAlcoholSubstanceOne - percentAlcoholSubstanceTwo);
        double substanceVolumeTwo = desiredVolume - substanceVolumeOne;
        calculationOutput.put("substanceVolumeOne", substanceVolumeOne);
        calculationOutput.put("substanceVolumeTwo", substanceVolumeTwo);
        return calculationOutput;
    }

    public static HashMap solveForTotalVolumeAndVolumePart(double substanceVolumeOne, double desiredPercentAlcohol, double percentAlcoholSubstanceOne, double percentAlcoholSubstanceTwo) {
        HashMap calculationOutput = new HashMap();
        double desiredVolume = (substanceVolumeOne * percentAlcoholSubstanceOne - substanceVolumeOne * percentAlcoholSubstanceTwo)/(desiredPercentAlcohol - percentAlcoholSubstanceTwo);
        double substanceVolume = desiredVolume - substanceVolumeOne;
        calculationOutput.put("substanceVolume", substanceVolume);
        calculationOutput.put("desiredVolume", desiredVolume);
        return calculationOutput;
    }

    public static HashMap solveForPercentAlcohol(double desiredVolume, double substanceVolumeOne, double substanceVolumeTwo, double percentAlcoholSubstanceOne, double percentAlcoholSubstanceTwo) {
        HashMap calculationOutput = new HashMap();
        double desiredPercentAlcohol = (substanceVolumeOne * percentAlcoholSubstanceOne + substanceVolumeTwo * percentAlcoholSubstanceTwo)/desiredVolume;
        calculationOutput.put("desiredPercentAlcohol", desiredPercentAlcohol);
        return calculationOutput;
    }

    public static HashMap detectErrors(boolean missingDesiredVolume, boolean missingDesiredPercentAlcohol,
                                 boolean missingSubstanceVolumeOne, boolean missingPercentAlcoholSubstanceOne,
                                 boolean missingSubstanceVolumeTwo, boolean missingPercentAlcoholSubstanceTwo,
                                 double desiredVolume, double substanceVolumeOne,
                                 double substanceVolumeTwo, double desiredPercentAlcohol,
                                 double percentAlcoholSubstanceOne, double percentAlcoholSubstanceTwo,
                                 int missingCount
    ){
        HashMap detectErrorsResponse = new HashMap();
        detectErrorsResponse.put("hasErrors", true);

        if(!missingDesiredPercentAlcohol && (desiredPercentAlcohol > 100 || desiredPercentAlcohol < 0)){
            detectErrorsResponse.put("errorType", );
        }
        else if(!missingPercentAlcoholSubstanceOne && (percentAlcoholSubstanceOne > 100 || percentAlcoholSubstanceOne < 0)){
            detectErrorsResponse.put("errorType", );
        }
        else if(!missingPercentAlcoholSubstanceTwo && (percentAlcoholSubstanceTwo > 100 || percentAlcoholSubstanceTwo < 0) ){
            detectErrorsResponse.put("errorType", );
        }
        else if(!missingPercentAlcoholSubstanceOne && ! missingPercentAlcoholSubstanceTwo && (percentAlcoholSubstanceOne == percentAlcoholSubstanceTwo)){
            detectErrorsResponse.put("errorType", );
        }
        else if(!missingDesiredVolume && desiredVolume < 0){
            detectErrorsResponse.put("errorType", );
        }
        else if(!missingSubstanceVolumeTwo && substanceVolumeOne < 0){
            detectErrorsResponse.put("errorType", );
        }
        else if(!missingSubstanceVolumeTwo && substanceVolumeTwo < 0){
            detectErrorsResponse.put("errorType", );
        }
        else if (missingPercentAlcoholSubstanceOne && missingPercentAlcoholSubstanceTwo) {
            detectErrorsResponse.put("errorType", );
        }
        else if (missingPercentAlcoholSubstanceOne) {
            detectErrorsResponse.put("errorType", );
        }
        else if (missingPercentAlcoholSubstanceTwo) {
            detectErrorsResponse.put("errorType", );
        }
        else if (missingCount > 2) {
            detectErrorsResponse.put("errorType", );
        }
        else if(!missingDesiredVolume && !missingSubstanceVolumeOne && !missingSubstanceVolumeTwo
                && desiredVolume != (substanceVolumeOne + substanceVolumeTwo)){
            detectErrorsResponse.put("errorType", );
        }
        else if(!missingDesiredPercentAlcohol && !missingPercentAlcoholSubstanceOne && !missingPercentAlcoholSubstanceTwo){
            if(desiredPercentAlcohol > percentAlcoholSubstanceOne && desiredPercentAlcohol > percentAlcoholSubstanceTwo){
                detectErrorsResponse.put("errorType", );
            }
            else if(desiredPercentAlcohol < percentAlcoholSubstanceOne && desiredPercentAlcohol < percentAlcoholSubstanceTwo){
                detectErrorsResponse.put("errorType", );
            }
            else if(desiredPercentAlcohol == percentAlcoholSubstanceOne || desiredPercentAlcohol == percentAlcoholSubstanceTwo){
                detectErrorsResponse.put("errorType", );
            }
            else {
                detectErrorsResponse.put("hasErrors", false);
            }
        }
        else {
            detectErrorsResponse.put("hasErrors", false);
        }
        return detectErrorsResponse;
    }
}
