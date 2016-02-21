package test.com.firstapp;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Assert;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.JexlScript;
import org.apache.commons.jexl3.JxltEngine;
import org.apache.commons.jexl3.MapContext;
import org.apache.commons.jexl3.internal.Engine;

import java.util.Map;

public class FirstActivity extends Activity {
    public String str ="";
    String num, tempNum;
    EditText showResult;
    TextView viewText;
    Boolean clear=false;



    public String exec(String expr)  {
        JexlEngine jexl = new JexlBuilder().create();
        final JexlContext context = new MapContext();
        JexlScript script=null;
        Object result ="";
        try {
            script = jexl.createScript(expr);
            System.out.println("Expression::::> "+expr);
            result = script.execute(context, script);
        }catch (Exception e){
            if(!clear) {
                expr = expr.substring(0, expr.length() - 1);
                script = jexl.createScript(expr);
                System.out.println("Expression::::> " + expr);
                result = script.execute(context, script);
            }
        }
       return result.toString();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_first);
        showResult = (EditText)findViewById(R.id.result_id);
        viewText=(TextView)findViewById(R.id.textView);
    }

    public void btn0Clicked(View v){
        clearCalc();
        insert("0");
    }
    public void btn1Clicked(View v){
        clearCalc();
        insert("1");
    }
    public void btn2Clicked(View v){
        clearCalc();
        insert("2");
    }
    public void btn3Clicked(View v){
        clearCalc();
        insert("3");
    }
    public void btn4Clicked(View v){
        clearCalc();
        insert("4");
    }
    public void btn5Clicked(View v){
        clearCalc();
        insert("5");
    }
    public void btn6Clicked(View v){
        clearCalc();
        insert("6");
    }
    public void btn7Clicked(View v){
        clearCalc();
        insert("7");
    }

    private void clearCalc() {
        if(clear) {
            clearContents();
        }
    }

    public void btn8Clicked(View v){
        clearCalc();
        insert("8");
    }
    public void btn9Clicked(View v){
        clearCalc();
        insert("9");
    }
    public void btnDotClicked(View v){
        clearCalc();
        System.out.println("DotClicked:::> "+showResult.getText().toString());
        if(showResult.getText().toString().contains(".")) {
            insert("");
        }else{
            insert(".");
        }
    }

    public void btnplusClicked(View v){
        clearCalc();
        if(num.compareTo("")!=0) {
            perform();
            viewText.append("+");
        }
    }
    public void btnminusClicked(View v){
        clearCalc();
        if(num.compareTo("")!=0) {
            perform();
            viewText.append("-");
        }
    }
    public void btndivideClicked(View v){
        clearCalc();
        if(num.compareTo("")!=0) {
            perform();
            viewText.append("/");
        }
    }
    public void btnmultiClicked(View v){
        clearCalc();
        if(num.compareTo("")!=0) {
            perform();
            viewText.append("*");
        }
    }
    public void btnequalClicked(View v){
        calculate();
    }
    public void btnclearClicked(View v){
        reset();
    }

    public void btnBackClicked(View v){
        String text=showResult.getText().toString();
        if(text!=null&&text.compareTo("")!=0) {
            text = text.substring(0, text.length() - 1);
            showResult.setText(text);
            str=text;
            num=text;
        }
        //num="";
    }
    private void reset() {
        num = "";
        str="";
        showResult.setText("");
        viewText.setText("");
    }
    private void insert(String j) {
        str = str+j;
        num=new String(str);
        showResult.setText(str);
    }

    private void clearContents(){
        str = "";
        //num = "";
        viewText.setText("");
        showResult.setText("");
        clear=Boolean.FALSE;
    }
    private void perform() {
        str = "";
        System.out.println("str is:::> "+str+" num is: "+num);
        if(num!=null&&num.compareTo("")!=0) {
            num = num.replaceFirst("^0+(?!$)", "");
            viewText.append(num);
            System.out.println("Text::::> " + viewText.getText());
        }
        num="";
    }
    private void calculate() {
        if(num!=null&&num.compareTo("")!=0) {
            viewText.append(num);
            String dispTxt = viewText.getText().toString();
            num = num.replaceFirst("^0+(?!$)", "");
            System.out.println("Calculate Display Text:::> " + dispTxt);
            if (dispTxt != null && dispTxt.compareTo("") != 0) {
                String result = exec(dispTxt);
                if (result != null && result.compareTo("") != 0) {
                    viewText.append("= " + result);
                    showResult.setText("" + result);
                    num = result;
                    clear = true;
                } else {
                    num = "";
                }
            } else {
                num = "";
            }
            // num="";
        }
    }
}
