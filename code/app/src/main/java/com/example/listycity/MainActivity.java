package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    LinearLayout secondLayout;
    EditText cityInput;
    Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Beirut"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // References to the second layout, EditText, and buttons
        secondLayout = findViewById(R.id.second_top_layout);
        Button addCityButton = findViewById(R.id.add_city_button);
        cityInput = findViewById(R.id.City_input);
        confirmButton = findViewById(R.id.confirm_city_button);

        // Show the second layout when "Add City" is pressed
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondLayout.setVisibility(View.VISIBLE);
            }
        });

        // Add the city to the list when "Confirm" is pressed
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCity = cityInput.getText().toString().trim(); // Get the input city
                if (!newCity.isEmpty()) { // Only add non-empty input
                    dataList.add(newCity); // Add to the list
                    cityAdapter.notifyDataSetChanged(); // Refresh the adapter
                    cityInput.setText(""); // Clear the input field
                    secondLayout.setVisibility(View.GONE); // Hide the second layout
                }
            }
        });
    }
}