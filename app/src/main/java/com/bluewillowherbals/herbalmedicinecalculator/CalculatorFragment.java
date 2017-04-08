package com.bluewillowherbals.herbalmedicinecalculator;
//TODO error types constants in calcActivity
//TODO move simple volume calcs to activity
//TODO button layout maybe fixed/floating footer?
//Todo help button and modal
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalculatorFragment extends Fragment implements View.OnClickListener {

    private View layout;
    private double desiredVolume;
    private double substanceVolumeOne;
    private double substanceVolumeTwo;
    private double percentAlcoholSubstanceOne;
    private double percentAlcoholSubstanceTwo;
    private double desiredPercentAlcohol;

    private boolean missingDesiredVolume;
    private boolean missingSubstanceVolumeOne;
    private boolean missingSubstanceVolumeTwo;
    private boolean missingPercentAlcoholSubstanceOne;
    private boolean missingPercentAlcoholSubstanceTwo;
    private boolean missingDesiredPercentAlcohol;
    private int missingCount;
    private HashMap calculationOutput;

    private TextInputLayout desiredVolumeTextInputLayout;
    private TextInputLayout volumeSubstance1TextInputLayout;
    private TextInputLayout volumeSubstance2TextInputLayout;
    private TextInputLayout desiredAlcoholPercentageTextInputLayout;
    private TextInputLayout alcoholPercentageSubstance1TextInputLayout;
    private TextInputLayout alcoholPercentageSubstance2TextInputLayout;

    private EditText desiredVolumeEntry;
    private EditText substanceVolumeOneEntry;
    private EditText substanceVolumeTwoEntry;
    private EditText desiredPercentAlcoholEntry;
    private EditText percentAlcoholSubstanceOneEntry;
    private EditText percentAlcoholSubstanceTwoEntry;

    public void initialize(){
        clearErrors();
        missingDesiredVolume = false;
        missingSubstanceVolumeOne = false;
        missingSubstanceVolumeTwo = false;
        missingPercentAlcoholSubstanceOne = false;
        missingPercentAlcoholSubstanceTwo = false;
        missingDesiredPercentAlcohol = false;
        missingCount = 0;
    }

    public void calculate(View view){
        clearErrors();

        //get values or mark as missing
        if(!isEmpty(desiredVolumeEntry)){
            desiredVolume = Double.parseDouble(desiredVolumeEntry.getText().toString());
        }
        else {
            missingDesiredVolume = true;
            missingCount++;
        }

        if(!isEmpty(substanceVolumeOneEntry)){
            substanceVolumeOne = Double.parseDouble(substanceVolumeOneEntry.getText().toString());
        }
        else {
            missingSubstanceVolumeOne = true;
            missingCount++;
        }

        if(!isEmpty(substanceVolumeTwoEntry)){
            substanceVolumeTwo = Double.parseDouble(substanceVolumeTwoEntry.getText().toString());
        }
        else{
            missingSubstanceVolumeTwo = true;
            missingCount++;
        }

        if(!isEmpty(desiredPercentAlcoholEntry)){
            desiredPercentAlcohol = Double.parseDouble(desiredPercentAlcoholEntry.getText().toString());
        }
        else {
            missingDesiredPercentAlcohol = true;
            missingCount++;
        }

        if(!isEmpty(percentAlcoholSubstanceOneEntry)){
            percentAlcoholSubstanceOne = Double.parseDouble(percentAlcoholSubstanceOneEntry.getText().toString());
        }
        else {
            missingPercentAlcoholSubstanceOne = true;
            missingCount++;
        }

        if(!isEmpty(percentAlcoholSubstanceTwoEntry)){
            percentAlcoholSubstanceTwo = Double.parseDouble(percentAlcoholSubstanceTwoEntry.getText().toString());
        }
        else {
            missingPercentAlcoholSubstanceTwo = true;
            missingCount++;
        }

        //see if we can figure out volumes immediatly
        simpleVolumeCalculation();

        //detect input errors and display proper error messages
        HashMap checkForErrors = CalculatorActivity.detectErrors(missingDesiredVolume, missingDesiredPercentAlcohol,
            missingSubstanceVolumeOne, missingPercentAlcoholSubstanceOne,
            missingSubstanceVolumeTwo, missingPercentAlcoholSubstanceTwo,
            desiredVolume, substanceVolumeOne,
            substanceVolumeTwo, desiredPercentAlcohol,
            percentAlcoholSubstanceOne, percentAlcoholSubstanceTwo, missingCount
        );

        if ((boolean) checkForErrors.get("hasErrors")){
            String errorType = (String) checkForErrors.get("errorType");
            String missingCountString = Integer.toString(missingCount);

            if(errorType == ){
                desiredAlcoholPercentageTextInputLayout.setError(getString(R.string.percent_alcohol_bounds_error));
                desiredAlcoholPercentageTextInputLayout.requestFocus();
            }
            else if(errorType == ){
                alcoholPercentageSubstance1TextInputLayout.setError(getString(R.string.percent_alcohol_bounds_error));
                alcoholPercentageSubstance1TextInputLayout.requestFocus();
            }
            else if(errorType == ){
                alcoholPercentageSubstance2TextInputLayout.setError(getString(R.string.percent_alcohol_bounds_error));
                alcoholPercentageSubstance2TextInputLayout.requestFocus();
            }
            else if(errorType == ){
                alcoholPercentageSubstance1TextInputLayout.setError(getString(R.string.equal_parts_error));
                alcoholPercentageSubstance1TextInputLayout.requestFocus();
                alcoholPercentageSubstance2TextInputLayout.setError(getString(R.string.equal_parts_error));
            }
            else if(errorType == ){
                desiredVolumeTextInputLayout.setError(getString(R.string.volume_bounds_error));
                desiredVolumeTextInputLayout.requestFocus();
            }
            else if(errorType == ){
                volumeSubstance1TextInputLayout.setError(getString(R.string.volume_bounds_error));
                volumeSubstance1TextInputLayout.requestFocus();
            }
            else if(errorType == ){
                volumeSubstance2TextInputLayout.setError(getString(R.string.volume_bounds_error));
                volumeSubstance2TextInputLayout.requestFocus();
            }
            else if (errorType == ) {
                alcoholPercentageSubstance1TextInputLayout.setError(getString(R.string.required_field_error));
                alcoholPercentageSubstance1TextInputLayout.requestFocus();
                alcoholPercentageSubstance2TextInputLayout.setError(getString(R.string.required_field_error));
            }
            else if (errorType == ) {
                alcoholPercentageSubstance1TextInputLayout.setError(getString(R.string.required_field_error));
                alcoholPercentageSubstance1TextInputLayout.requestFocus();
            }
            else if (errorType == ) {
                alcoholPercentageSubstance2TextInputLayout.setError(getString(R.string.required_field_error));
                alcoholPercentageSubstance2TextInputLayout.requestFocus();
            }
            else if (errorType == ) {
                desiredVolumeTextInputLayout.setError(missingCountString + getString(R.string.empty_field_error));
                desiredVolumeTextInputLayout.requestFocus();
            }
            else if (errorType == ) {
                desiredAlcoholPercentageTextInputLayout.setError(missingCountString + getString(R.string.empty_field_error));
                desiredAlcoholPercentageTextInputLayout.requestFocus();
            }
            else if (errorType == ) {
                volumeSubstance1TextInputLayout.setError(missingCountString + getString(R.string.empty_field_error));
                volumeSubstance1TextInputLayout.requestFocus();
            }
            else if (errorType == ) {
                volumeSubstance2TextInputLayout.setError(missingCountString + getString(R.string.empty_field_error));
                volumeSubstance2TextInputLayout.requestFocus();
            }
            else if(errorType == ){
                desiredVolumeTextInputLayout.setError(getString(R.string.volume_total_error));
                desiredVolumeTextInputLayout.requestFocus();
                volumeSubstance1TextInputLayout.setError(getString(R.string.volume_total_error));
                volumeSubstance2TextInputLayout.setError(getString(R.string.volume_total_error));
            }
            else if(errorType == ){
                desiredAlcoholPercentageTextInputLayout.setError(getString(R.string.desired_percent_alcohol_high_error));
                desiredAlcoholPercentageTextInputLayout.requestFocus();
                alcoholPercentageSubstance1TextInputLayout.setError(getString(R.string.desired_percent_alcohol_high_error));
                alcoholPercentageSubstance2TextInputLayout.setError(getString(R.string.desired_percent_alcohol_high_error));
            }
            else if(errorType == ){
                desiredAlcoholPercentageTextInputLayout.setError(getString(R.string.desired_percent_alcohol_low_error));
                desiredAlcoholPercentageTextInputLayout.requestFocus();
                alcoholPercentageSubstance1TextInputLayout.setError(getString(R.string.desired_percent_alcohol_low_error));
                alcoholPercentageSubstance2TextInputLayout.setError(getString(R.string.desired_percent_alcohol_low_error));
            }
            else if(errorType == ){
                desiredAlcoholPercentageTextInputLayout.setError(getString(R.string.desired_equals_part_error));
                desiredAlcoholPercentageTextInputLayout.requestFocus();
                alcoholPercentageSubstance1TextInputLayout.setError(getString(R.string.desired_equals_part_error));
                alcoholPercentageSubstance2TextInputLayout.setError(getString(R.string.desired_equals_part_error));
            }
        }
        else{
            //run calculation
            if (missingCount == 2) {
                //route to proper calculation
                if (missingDesiredVolume && missingSubstanceVolumeOne) {
                    HashMap calculationResults = CalculatorActivity.solveForTotalVolumeAndVolumePart(substanceVolumeTwo, desiredPercentAlcohol, percentAlcoholSubstanceTwo, percentAlcoholSubstanceOne);
                    desiredVolume = (double) calculationResults.get("desiredVolume");
                    substanceVolumeOne = (double) calculationResults.get("substanceVolume");

                    desiredVolumeEntry.setText(Double.toString(desiredVolume));
                    substanceVolumeOneEntry.setText(Double.toString(substanceVolumeOne));
                } else if (missingDesiredVolume && missingSubstanceVolumeTwo) {
                    HashMap calculationResults = CalculatorActivity.solveForTotalVolumeAndVolumePart(substanceVolumeOne, desiredPercentAlcohol, percentAlcoholSubstanceOne, percentAlcoholSubstanceTwo);
                    desiredVolume = (double) calculationResults.get("desiredVolume");
                    substanceVolumeTwo = (double) calculationResults.get("substanceVolume");

                    desiredVolumeEntry.setText(Double.toString(desiredVolume));
                    substanceVolumeTwoEntry.setText(Double.toString(substanceVolumeTwo));
                } else if (missingSubstanceVolumeOne && missingSubstanceVolumeTwo) {
                    HashMap calculationResults = CalculatorActivity.solveForVolumeParts(desiredVolume, desiredPercentAlcohol, percentAlcoholSubstanceOne, percentAlcoholSubstanceTwo);
                    substanceVolumeOne = (double) calculationResults.get("substanceVolumeOne");
                    substanceVolumeTwo = (double) calculationResults.get("substanceVolumeTwo");

                    substanceVolumeOneEntry.setText(Double.toString(substanceVolumeOne));
                    substanceVolumeTwoEntry.setText(Double.toString(substanceVolumeTwo));
                }
            } else if (missingCount == 1) {
                if (missingDesiredPercentAlcohol) {
                    HashMap calculationResults = CalculatorActivity.solveForPercentAlcohol(desiredVolume, substanceVolumeOne, substanceVolumeTwo, percentAlcoholSubstanceOne, percentAlcoholSubstanceTwo);
                    desiredPercentAlcohol = (double) calculationResults.get("desiredPercentAlcohol");

                    desiredPercentAlcoholEntry.setText(Double.toString(desiredPercentAlcohol));
                } else {
                    //We should never get here because of previous field requirements
                    //in detectErrors
                }
            } else {
                //All fields filled nothing to solve
            }
        }
    }

    private boolean isEmpty(EditText edText){
        return edText.getText().toString().trim().length() == 0;
    }

    private void clear(View view){

        desiredVolumeEntry.getText().clear();
        desiredPercentAlcoholEntry.getText().clear();
        substanceVolumeOneEntry.getText().clear();
        substanceVolumeTwoEntry.getText().clear();
        percentAlcoholSubstanceOneEntry.getText().clear();
        percentAlcoholSubstanceTwoEntry.getText().clear();

        initialize();

    }

    private void clearErrors(){
        desiredVolumeTextInputLayout.setError(null);
        volumeSubstance1TextInputLayout.setError(null);
        volumeSubstance2TextInputLayout.setError(null);
        desiredAlcoholPercentageTextInputLayout.setError(null);
        alcoholPercentageSubstance1TextInputLayout.setError(null);
        alcoholPercentageSubstance2TextInputLayout.setError(null);
    }

    private void simpleVolumeCalculation(){
        if(missingDesiredVolume && !missingSubstanceVolumeOne && !missingSubstanceVolumeTwo){
            desiredVolume = substanceVolumeOne + substanceVolumeTwo;
            missingDesiredVolume = false;
            missingCount--;
            desiredVolumeEntry.setText(Double.toString(desiredVolume));
        }
        else if(!missingDesiredVolume && missingSubstanceVolumeOne && !missingSubstanceVolumeTwo){
            substanceVolumeOne = desiredVolume - substanceVolumeTwo;
            missingSubstanceVolumeOne = false;
            missingCount--;
            substanceVolumeOneEntry.setText(Double.toString(substanceVolumeOne));
        }
        else if(!missingDesiredVolume && !missingSubstanceVolumeOne && missingSubstanceVolumeTwo){
            substanceVolumeTwo = desiredVolume - substanceVolumeOne;
            missingSubstanceVolumeTwo = false;
            missingCount--;
            substanceVolumeTwoEntry.setText(Double.toString(substanceVolumeTwo));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.fragment_calculator, container, false);

        //attach click listener to buttons
        Button calculateButton = (Button) layout.findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(this);

        Button clearButton = (Button) layout.findViewById(R.id.clear_button);
        clearButton.setOnClickListener(this);

        Button calculateButtonTop = (Button) layout.findViewById(R.id.calculate_button_top);
        calculateButtonTop.setOnClickListener(this);

        Button clearButtonTop = (Button) layout.findViewById(R.id.clear_button_top);
        clearButtonTop.setOnClickListener(this);

        //initialize TextInputLayouts to have proper hints
        desiredVolumeTextInputLayout = (TextInputLayout) layout.findViewById(R.id.desired_volume_text_input_layout);
        desiredVolumeTextInputLayout.setHint(getString(R.string.desired_volume_hint));

        volumeSubstance1TextInputLayout = (TextInputLayout) layout.findViewById(R.id.substance_1_volume_text_input_layout);
        volumeSubstance1TextInputLayout.setHint(getString(R.string.volume_substance_1_hint));

        volumeSubstance2TextInputLayout = (TextInputLayout) layout.findViewById(R.id.substance_2_volume_text_input_layout);
        volumeSubstance2TextInputLayout.setHint(getString(R.string.volume_substance_2_hint));

        desiredAlcoholPercentageTextInputLayout = (TextInputLayout) layout.findViewById(R.id.desired_alcohol_percentage_text_input_layout);
        desiredAlcoholPercentageTextInputLayout.setHint(getString(R.string.desired_alcohol_percentage_hint));

        alcoholPercentageSubstance1TextInputLayout = (TextInputLayout) layout.findViewById(R.id.substance_1_alcohol_percentage_text_input_layout);
        alcoholPercentageSubstance1TextInputLayout.setHint(getString(R.string.alcohol_percentage_substance_1_hint));

        alcoholPercentageSubstance2TextInputLayout = (TextInputLayout) layout.findViewById(R.id.substance_2_alcohol_percentage_text_input_layout);
        alcoholPercentageSubstance2TextInputLayout.setHint(getString(R.string.alcohol_percentage_substance_2_hint));

        //set private references to editTexts
        desiredVolumeEntry = (EditText) layout.findViewById(R.id.desired_volume_entry);
        substanceVolumeOneEntry = (EditText) layout.findViewById(R.id.volume_substance_1_entry);
        substanceVolumeTwoEntry = (EditText) layout.findViewById(R.id.volume_substance_2_entry);
        desiredPercentAlcoholEntry = (EditText) layout.findViewById(R.id.desired_alcohol_percentage_entry);
        percentAlcoholSubstanceOneEntry = (EditText) layout.findViewById(R.id.alcohol_percentage_substance_1_entry);
        percentAlcoholSubstanceTwoEntry = (EditText) layout.findViewById(R.id.alcohol_percentage_substance_2_entry);

        return layout;
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.calculate_button:
                calculate(v);
                break;
            case R.id.clear_button:
                clear(v);
                break;
            case R.id.calculate_button_top:
                calculate(v);
                break;
            case R.id.clear_button_top:
                clear(v);
                break;
        }
    }
}
