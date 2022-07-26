package com.example.sqlitecheck;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlitecheck.DBHandler;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText courseNameEdt, courseTracksEdt, courseDurationEdt, courseDescriptionEdt;
    private Button addCourseBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing all our variables.
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseTracksEdt = findViewById(R.id.idEdtCourseTracks);
        courseDurationEdt = findViewById(R.id.idEdtCourseDuration);
        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);
        addCourseBtn = findViewById(R.id.idBtnAddCourse);

        dbHandler = new DBHandler(MainActivity.this);

        // below line is to add on click listener for our add course button.
//        addCourseBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                // below line is to get data from all edit text fields.
//                String courseName = courseNameEdt.getText().toString();
//                String courseTracks = courseTracksEdt.getText().toString();
//                String courseDuration = courseDurationEdt.getText().toString();
//                String courseDescription = courseDescriptionEdt.getText().toString();
//
//                // validating if the text fields are empty or not.
//                if (courseName.isEmpty() && courseTracks.isEmpty() && courseDuration.isEmpty() && courseDescription.isEmpty()) {
//                    Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // on below line we are calling a method to add new
//                // course to sqlite data and pass all our values to it.
//                dbHandler.addNewEmployee(courseName, courseTracks);
//
//                // after adding the data we are displaying a toast message.
//                Toast.makeText(MainActivity.this, "Course has been added.", Toast.LENGTH_SHORT).show();
//                courseNameEdt.setText("");
//                courseDurationEdt.setText("");
//                courseTracksEdt.setText("");
//                courseDescriptionEdt.setText("");
//            }
//        });

        Employee hargun = new Employee("hargun","10000");
        dbHandler.addNewEmployee(hargun);

        Employee x = new Employee(1,"Harman","99999");
        dbHandler.update(x);
//

//        dbHandler.deleteById(4);

//        dbHandler.deleteByName("hargun");

        int count = dbHandler.getTotalNumber();
        Log.e("count",Integer.toString(count));
        List<Employee> all = dbHandler.display();
        for(int i =0; i<all.size();i++){
            Log.e("show","\nID: "+all.get(i).getId()+"\nName: "+all.get(i).getName()+"\nINCREMENT: "+all.get(i).getIncrement());
        }

//        Employee req = dbHandler.getEmployee(1);
//        Log.e("show","\nID: "+req.getId()+"\nName: "+req.getName()+"\nINCREMENT: "+req.getIncrement());
    }
}
