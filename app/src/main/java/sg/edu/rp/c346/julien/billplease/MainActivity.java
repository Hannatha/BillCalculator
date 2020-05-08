package sg.edu.rp.c346.julien.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    ToggleButton splittoggle;
    ToggleButton resettoggle;


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
        splittoggle = findViewById(R.id.splittoggle);
        resettoggle = findViewById(R.id.resettoggle);

        splittoggle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(editAmount.getText().toString().trim().length() !=0 && editPax.getText().toString().trim().length() !=0){
                    double newAmt=0;
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
                    totalbillDisplay.setText("Total Bill: $" + String.format("%.2f",newAmt));
                    int personpax = Integer.parseInt(editPax.getText().toString());
                    if(personpax !=1){
                        eachpaymentDisplay.setText("Each pays: $" + String.format("%.2f",newAmt/personpax));
                    }
                    else{
                        eachpaymentDisplay.setText("Each pays: $" + newAmt);
                    }
                }
            }
        });

    }
}
