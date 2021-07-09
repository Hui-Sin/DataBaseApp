package sg.edu.rp.c346.id20018354.databaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTask,etDate;
    Button btnInsert,btnGetTasks;
    TextView tvResults;
    ListView lvTasks;
    Spinner spnisAsecend;

    ArrayList<String> alTasks;
    ArrayAdapter<String> aaTasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask=findViewById(R.id.editTextTask);
        etDate=findViewById(R.id.editTextTaskDate);
        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks=findViewById(R.id.btnGetTasks);
        tvResults=findViewById(R.id.tvResults);
        lvTasks=findViewById(R.id.lvtask);
        spnisAsecend=findViewById(R.id.spnisAcend);

        alTasks = new ArrayList<>();
        aaTasks = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,alTasks);
        lvTasks.setAdapter(aaTasks);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = String.valueOf(etTask.getText());
                String date = String.valueOf(etDate.getText());
                DBHelper db = new DBHelper(MainActivity.this);
                db.insertTask(task, date);
            }
        });
//        btnGetTasks.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                DBHelper db = new DBHelper(MainActivity.this);
//                ArrayList<String> data = db.getTaskContent();
//                db.close();
//                String txt = "";
//                for (int i = 0; i < data.size(); i++) {
//                    Log.d("Database Content", i +". "+data.get(i));
//                    txt += i + ". " + data.get(i) + "\n";
//                }
//                tvResults.setText(txt);
//            }
//        });
        spnisAsecend.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:

                        btnGetTasks.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alTasks.clear();
                                DBHelper db = new DBHelper(MainActivity.this);
                                ArrayList<Task> data = db.getTasks(true);
                                db.close();
                                String txt = "";
                                for (int i = 0; i < data.size(); i++) {
                                    Log.d("Database Content", i +". "+data.get(i));
                                    txt += i + ". " + data.get(i) + "\n";
                                    alTasks.add(i+ ". "+ data.get(i).toString());
                                }
                                tvResults.setText(txt);
                                aaTasks.notifyDataSetChanged();
                            }
                        });
                        break;
                    case 1:
                        btnGetTasks.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alTasks.clear();
                                DBHelper db = new DBHelper(MainActivity.this);
                                ArrayList<Task> data = db.getTasks(false);
                                db.close();
                                String txt = "";
                                for (int i = 0; i < data.size(); i++) {
                                    Log.d("Database Content", i +". "+data.get(i));
                                    txt += i + ". " + data.get(i) + "\n";
                                    alTasks.add(i+ ". "+ data.get(i).toString());
                                }
                                tvResults.setText(txt);
                                aaTasks.notifyDataSetChanged();
                            }
                        });
                        break;
//               tvChoice.setText(String.format("Spinner choice '%s' selected", spinChoice.getSelectedItem()));
//               if(position==0){tv.setText("Spinner Item, Yes Selected");}else if (position==1){tv.setText("Spinner Item, No Selected");}
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}