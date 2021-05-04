package sg.edu.rp.c346.id20011155.l03problemstatement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button rst;
    Button splt;
    ToggleButton svs;
    ToggleButton gst;
    TextView ttlBill;
    TextView eachPay;
    EditText enter_amount;
    EditText num_of_pax;
    EditText discInput;
    RadioGroup rgPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rst = findViewById(R.id.rst);
        splt = findViewById(R.id.splt);
        svs = findViewById(R.id.svs);
        gst = findViewById(R.id.gst);
        ttlBill = findViewById(R.id.ttlBill);
        eachPay = findViewById(R.id.eachPay);
        enter_amount = findViewById(R.id.enter_amount);
        num_of_pax = findViewById(R.id.num_of_pax);
        discInput = findViewById(R.id.discInput);
        rgPayment = findViewById(R.id.rgPayment);

        splt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(num_of_pax.getText().toString().length() != 0 && enter_amount.getText().toString().length() != 0) {
                   double ttlAmt = 0.0;

                   if (svs.isChecked() && gst.isChecked()) {
                       ttlAmt = Double.parseDouble(enter_amount.getText().toString()) * 1.17;
                   } else if (!svs.isChecked() && !gst.isChecked()) {
                       ttlAmt = Double.parseDouble(enter_amount.getText().toString()) * 1;
                   } else if (gst.isChecked() && !svs.isChecked()) {
                       ttlAmt = Double.parseDouble(enter_amount.getText().toString()) * 1.07;
                   } else {
                       ttlAmt = Double.parseDouble(enter_amount.getText().toString()) * 1.1;
                   }

                   if (discInput.getText().toString().length() != 0) {
                       ttlAmt *= 1 - (Double.parseDouble(discInput.getText().toString()) / 100);
                   }

                   ttlBill.setText("Total Bill: $" + String.format("%.2f", ttlAmt));
                   int ttlPeople = Integer.parseInt(num_of_pax.getText().toString());
                   double perPerson = ttlAmt/ttlPeople;
                   if (ttlPeople > 1) {
                           eachPay.setText("Each Pays: $" + String.format("%.2f", perPerson));
                   } else if (ttlPeople > 1) {
                       eachPay.setText("Each Pays: $" + String.format("%.2f", perPerson));
                   }
               }
            }
        });
        rst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discInput.setText("");
                num_of_pax.setText("");
                enter_amount.setText("");
            }
        });
    }
}