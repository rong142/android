package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    static String strOutput = "";

    private boolean lastIsOperator; //紀錄當前是否進行運算
    private String lastOperators = ""; //上一個運算元

    private double firstNumber = 0; //第一次的取值 double
    private double secondNumber = 0; //第二次的取值 double

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_linear);
        //初始化
    }

    /**
     * Called when the user taps the Send button
     */
    /*
    public void sendMessage(View view) {
            Intent intent = new Intent(this, DisplayMessageActivity.class);
            EditText editText = (EditText) findViewById(R.id.edit_message);
            String message = editText.getText().toString();
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }

     */

    public void buttonClick(View view) {
        EditText editText = (EditText) findViewById(R.id.editText_number);
        String numberStr = "";

        String myStringValue = editText.getText().toString();
        String operatorNumber = ""; //定義運算元
        /*
        if ( myStringValue.equals("0") ){
            editText.setText("");
        }*/

        operatorNumber = editText.getText().toString();
        if ( !lastOperators.equals("") ){
            int index = operatorNumber.lastIndexOf(lastOperators);
            operatorNumber = operatorNumber.substring(index+1);
        }

        switch (view.getId()) {
            case R.id.button0: {
                numberStr = "0";
                lastIsOperator = false;
                break;
            }
            case R.id.button1: {
                numberStr = "1";
                lastIsOperator = false;
                break;
            }
            case R.id.button2: {
                numberStr = "2";
                lastIsOperator = false;
                break;
            }
            case R.id.button3: {
                numberStr = "3";
                lastIsOperator = false;
                break;
            }
            case R.id.button4: {
                numberStr = "4";
                lastIsOperator = false;
                break;
            }
            case R.id.button5: {
                numberStr = "5";
                lastIsOperator = false;
                break;
            }
            case R.id.button6: {
                numberStr = "6";
                lastIsOperator = false;
                break;
            }
            case R.id.button7: {
                numberStr = "7";
                lastIsOperator = false;
                break;
            }
            case R.id.button8: {
                numberStr = "8";
                lastIsOperator = false;
                break;
            }
            case R.id.button9: {
                numberStr = "9";
                lastIsOperator = false;
                break;
            }
            case R.id.button_point: {
                numberStr = ".";
                lastIsOperator = false;
                break;
            }
            case R.id.button_divided: {
                if ((TextUtils.isEmpty(myStringValue) || (lastIsOperator) ) && !lastOperators.equals("=") ){
                    return;
                }
                opratorCalc(operatorNumber,"÷");
                lastIsOperator = true;
                numberStr = "÷";
                lastOperators = "÷";
                break;
            }
            case R.id.button_take: {
                if ((TextUtils.isEmpty(myStringValue) || (lastIsOperator) ) && !lastOperators.equals("=") ){
                    return;
                }
                opratorCalc(operatorNumber,"x");
                lastIsOperator = true;
                numberStr = "x";
                lastOperators = "x";
                break;
            }
            case R.id.button_minus: {
                if ((TextUtils.isEmpty(myStringValue) || (lastIsOperator) ) && !lastOperators.equals("=") ){
                    return;
                }
                opratorCalc(operatorNumber,"-");
                lastIsOperator = true;
                numberStr = "-";
                lastOperators = "-";
                break;
            }
            case R.id.button_plus: {
                if ((TextUtils.isEmpty(myStringValue) || (lastIsOperator) ) && !lastOperators.equals("=") ){
                    return;
                }
                opratorCalc(operatorNumber,"+");
                lastIsOperator = true;
                numberStr = "+";
                lastOperators = "+";
                break;
            }
            case R.id.button_AC: {
                lastIsOperator = false;
                strOutput = "";
                myStringValue = "";
                numberStr = "";
                firstNumber = 0;
                secondNumber = 0;
                lastOperators = "";
                operatorNumber = "";
                break;
            }
            case R.id.button_change: {
                if (myStringValue.equals(null)) {
                    return;
                } else {
                    double remainder = 0;
                    double factor = 0;
                    double d = Double.valueOf(myStringValue).doubleValue();
                    d = d * (-1);

                    String s = Double.toString(d);
                    strOutput = s;
                    numberStr = "";
                }
                break;
            }
            case R.id.button_percent: {
                double pd = Double.valueOf(myStringValue).doubleValue();
                double pr_pd = pd / 100;
                String s = Double.toString(pr_pd);
                strOutput = s;
                numberStr = "";
                break;
            }
            case R.id.button_equals: {
                double result = 0 ;
                if (TextUtils.isEmpty(lastOperators)){
                    return;
                }
                opratorResult(operatorNumber);
                secondNumber = 0;
                lastOperators = "=";
                lastIsOperator = true;
                strOutput = String.valueOf(firstNumber);
                break;
            }
        }
        strOutput = strOutput + numberStr;
        editText.setText(strOutput);
    }

    //運算操作
    private void operate(String operatorNumber) {
        //第二數 != 0
        if (secondNumber != 0) {
            if (lastOperators.equals("÷")) {
                secondNumber = secondNumber / Double.parseDouble(operatorNumber);
                firstNumber = firstNumber / secondNumber;
            } else if (lastOperators.equals("x")) {
                secondNumber = secondNumber * Double.parseDouble(operatorNumber);
                firstNumber = firstNumber * secondNumber;
            } else if (lastOperators.equals("+")) {
                secondNumber = Double.parseDouble(operatorNumber);
                firstNumber = firstNumber + secondNumber;
            } else if (lastOperators.equals("-")) {
                secondNumber = Double.parseDouble(operatorNumber);
                firstNumber = firstNumber - secondNumber;
            }
        }
        //第二數 = 0
        else {
            if (lastOperators.equals("÷")) {
                firstNumber = firstNumber / Double.parseDouble(operatorNumber);
            } else if (lastOperators.equals("x")) {
                firstNumber = firstNumber * Double.parseDouble(operatorNumber);
            } else if (lastOperators.equals("+")) {
                firstNumber = firstNumber + Double.parseDouble(operatorNumber);
            } else if (lastOperators.equals("-")) {
                firstNumber = firstNumber - Double.parseDouble(operatorNumber);
            }
        }
    }

        //傳回計算結果
        public void opratorResult( String operatorNumber ){
            operate(operatorNumber);
        }

        //將當前計算結果進行下一次的資料輸入及計算
        public void opratorCalc( String operatorNumber , String currentOprator ){
        if (TextUtils.isEmpty(lastOperators)){
            firstNumber = Double.parseDouble(operatorNumber);
            return;
        }
        if (lastOperators.equals(currentOprator)){
            if (lastOperators.equals("÷")){
                firstNumber = firstNumber / Double.parseDouble(operatorNumber);
            }else if (lastOperators.equals("x")){
                firstNumber = firstNumber * Double.parseDouble(operatorNumber);
            }else if (lastOperators.equals("+")){
                firstNumber = firstNumber + Double.parseDouble(operatorNumber);
            }else if (lastOperators.equals("-")){
                firstNumber = firstNumber - Double.parseDouble(operatorNumber);
            }
            return;
        }
        operate(operatorNumber);
    }
    }
