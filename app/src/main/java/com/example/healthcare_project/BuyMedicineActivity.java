package com.example.healthcare_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {

    private String[][] packages=
            {
                    {"Aldactone 25mg Tablet", "", "", "", "29.65"},
                    {"Allegra 180mg Tablet", "", "", "", "159"},
                    {"Andriol 40mg Capsule", "", "", "", "119.33"},
                    {"Benadryl Cough Syrup", "", "", "", "142.66"},
                    {"Carnitor 500mg Table", "", "", "", "289"},
                    {"Daflon 500mg Tablet", "", "", "", "104"},
                    {"Ebility Tablet", "", "", "", "102.60"},
                    {"Fabulas 80mg Tablet", "", "", "", "167"},
                    {"Galvus 50mg Tablet", "", "", "", "360.50"},
                    {"Morr F 5% Solution", "", "", "", "659.00"}
            };
    private String[] package_details={
            "Aldactone helps in curing symptoms of many health conditions which involve water retention into the body. Some of the disorders which respond well to Aldactone are given below:\n" +
                    "\n" +
                    "Hypertension\n" +
                    "Liver cirrhosis\n" +
                    "Cardiac failure\n" +
                    "Nephrotic syndrome\n" +
                    "Hypokalemia\n" +
                    "Conn's syndrome\n" +
                    "Hyperaldosteronism\n" +
                    "Peripheral edema\n" +
                    "Aldactone helps to treat symptoms of the above mentioned disorders owing to its property of expelling water from the body and reducing sodium retention. Patients should remain cautious while consuming the medicine and watch out for any ill effects of Aldactone.\n" +
                    "\n" +
                    "Side Effects of Aldactone:",
            "The therapeutic indications of Allerga are mentioned below:\n" +
                    "\n" +
                    "Allergic rhinitis\n" +
                    "Allergic conjunctivitis\n" +
                    "Hay fever\n" +
                    "Allergic laryngitis\n" +
                    "Urticaria\n" +
                    "Allergic dermatitis\n" +
                    "Insect bites\n" +
                    "Apart from the health conditions mentioned above, Allegra can be indicated in other allergic disorders as well.",
            "Andriol is used to treat hypogonadism in men. It is often prescribed to patients who have undergone testicle removal surgery for cancer treatment. Delayed puberty, male infertility are some of the disorders which respond well to Andriol.",
            "The therapeutic indications of Benadryl are as follows:\n" +
                    "\n" +
                    "Allergic bronchitis\n" +
                    "Allergic cough\n" +
                    "Uvulitis\n" +
                    "Other respiratory conditions which have not been mentioned above may also respond to Benadryl cough syrup.",
            "Following health disturbances can be altered by regular usage of Carnitor tablet:\n" +
                    "\n" +
                    "Primary carnitine deficiency\n" +
                    "Muscle pain\n" +
                    "Constant feeling of drowsiness\n" +
                    "Lack of enough vital energy\n" +
                    "Reduced muscle tone\n" +
                    "Carnitor tablet may be prescribed for disorders other than the ones mentioned above. The tablet should only be used if prescribed by a physician or a nutritionist (dietician).",
            "The therapeutic indications of Daflon are as follows:\n" +
                    "\n" +
                    "Varicose veins\n" +
                    "Swollen lymphatic vessels\n" +
                    "Hemorrhoids\n" +
                    "Sluggish blood flow\n" +
                    "Deep vein thrombosis\n" +
                    "Venous ulcers\n" +
                    "Oedema of the extremities\n" +
                    "Other diseases apart from those mentioned above can also be treated using Daflon.",
            "Every drug tends to develop some side effects if taken wrongly or taken without any medical indication. Development of side effects is also dependent on patients' sensitivity. Some of the side effects of Ebility are mentioned below:\n" +
                    "\n" +
                    "Nausea and vomiting\n" +
                    "Hyperacidity\n" +
                    "Abdominal pain\n" +
                    "Altered stool habits\n" +
                    "Dullness\n" +
                    "Light headedness\n" +
                    "Other symptoms of discomfort may also develop after consumption of Ebility. Patients should stop the medicine immediately and inform their doctor in such a case.",
            "Fabulas is used for symptomatic treatment of gout. It provides relief from symptoms like pain, swelling, soreness, stiffness, which are commonly observed in patients of gout. Gout invariably affects the joints and leads to deformity and reduction of joint function.",
            "Galvus is employed for the treatment of type 2 Diabetes which is associated with poor insulin metabolism. Pre-diabetes is a condition where patient displays high blood sugar on more than one occasion.\n" +
                    "Sometimes, diabetes may also be diagnosed during pregnancy (gestational diabetes) in women who may have been perfectly healthy before conceiving. Galvus can be used in treatment of all the above mentioned conditions, but only after a physician has prescribed them.",
            "The therapeutic indications of Morr F are as follows:\n" +
                    "\n" +
                    "Hair fall\n" +
                    "Loss of hair luster\n" +
                    "Thinness of hair\n" +
                    "Male pattern baldness\n" +
                    "Alopecia\n" +
                    "Apart from the above mentioned disorders, Morr F could be prescribed for other hair related disorders as well."
    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView listView;
    Button btnBack,btnGoToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        listView = findViewById(R.id.listView);
        btnBack = findViewById(R.id.btnBack);
        btnGoToCart = findViewById(R.id.btnGoToCart);

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i=0;i<packages.length;i++)
        {
            item = new HashMap<String,String>();
            item.put("Line1",packages[i][0]);
            item.put("Line2",packages[i][1]);
            item.put("Line3",packages[i][2]);
            item.put("Line4",packages[i][3]);
            item.put("Line5",packages[i][4]);
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"Line1","Line2","Line3","Line4","Line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(sa);

        //now when any item is clicked ,its details must be shown
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);
                //send some data along
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });
    }
}