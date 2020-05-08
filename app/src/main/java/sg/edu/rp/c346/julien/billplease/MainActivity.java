package sg.edu.rp.c346.julien.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    TextView amountDisplay;
    TextView paxDisplay;
    TextView discountDisplay;
    TextView totalbillDisplay;
    TextView eachpaymentDisplay;
    EditText editAmount;
    EditText editPax;
    EditText editDiscount;
    ToggleButton svstoggle;
    ToggleButton gsttoggle;
    Button splitbtn;
    Button resetbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amountDisplay = findViewById(R.id.amount);
        paxDisplay = findViewById(R.id.paxDisplay);
        discountDisplay = findViewById(R.id.discountDisplay);
        totalbillDisplay = findViewById(R.id.totalDisplay);
        eachpaymentDisplay = findViewById(R.id.eachpaymentDisplay);
        editAmount = findViewById(R.id.editamount);
        editPax = findViewById(R.id.editpax);
        editDiscount = findViewById(R.id.editDiscount);
        svstoggle = findViewById(R.id.svstoggle);
        gsttoggle = findViewById(R.id.gsttoggle);
        splitbtn = findViewById(R.id.splitbtn);
        resetbtn = findViewById(R.id.resetbtn);

        splitbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(editAmount.getText().toString().trim().length() !=0 && editPax.getText().toString().trim().length() !=0 && !editAmount.getText().toString().equals("0") && !editPax.getText().toString().equals("0")){
                    double newAmt;
                    if(!svstoggle.isChecked() && !gsttoggle.isChecked()){
                        newAmt = Double.parseDouble(editAmount.getText().toString());
                    }
                    else if (svstoggle.isChecked() && !gsttoggle.isChecked()){
                        newAmt = Double.parseDouble(editAmount.getText().toString())*1.1;
                    }
                    else if (!svstoggle.isChecked() && gsttoggle.isChecked()){
                        newAmt = Double.parseDouble(editAmount.getText().toString())*1.07;
                    }
                    else{
                        newAmt = Double.parseDouble(editAmount.getText().toString())*1.17;
                    }
                    if (editDiscount.getText().toString().trim().length() !=0){
                        newAmt *= 1 - Double.parseDouble(editDiscount.getText().toString()) / 100;
                    }
                    totalbillDisplay.setText(R.string.totalbill+ String.format("%.2f",newAmt));
                    int personpax = Integer.parseInt(editPax.getText().toString());
                    if(personpax !=1){
                        eachpaymentDisplay.setText(R.string.each_pays + String.format("%.2f",newAmt/personpax));
                    }
                    else{
                        eachpaymentDisplay.setText("Each pays: $" + newAmt);
                    }
                }
                else {
                    Toast toast = Toast.makeText(MainActivity.this, "Amount and pax cannot be empty!", Toast.LENGTH_SHORT);
                    TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
                    toastMessage.setTextColor(Color.RED);
                    toast.show();
                }
            }
        });
        resetbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast toast2 = Toast.makeText(MainActivity.this, "Calculation has been reset", Toast.LENGTH_SHORT);
                TextView toastMessage = (TextView) toast2.getView().findViewById(android.R.id.message);
                toastMessage.setTextColor(Color.RED);
                toast2.show();
                editDiscount.getText().clear();
                editAmount.getText().clear();
                editPax.getText().clear();
                totalbillDisplay.setText(R.string.totalbill);
                eachpaymentDisplay.setText(R.string.each_pays);
                svstoggle.setChecked(false);
                gsttoggle.setChecked(false);
            }
        });


    }
}
