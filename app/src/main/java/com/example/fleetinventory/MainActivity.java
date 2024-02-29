package com.example.fleetinventory;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText weightInput;
    private Spinner eVtolTypeSpinner;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightInput = findViewById(R.id.weightInput);
        eVtolTypeSpinner = findViewById(R.id.eVtolTypeSpinner);
        resultText = findViewById(R.id.resultText);

        setupWeightInputHint();
        setupEVtolTypeSpinner();

        findViewById(R.id.calculateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndDisplay();
            }
        });
    }

    private void setupWeightInputHint() {
        weightInput.setHint("Enter weight (lbs)");
        weightInput.setHintTextColor(ContextCompat.getColor(this, R.color.white));
    }

    private void setupEVtolTypeSpinner() {
        final List<String> eVtolTypes = new ArrayList<>();
        eVtolTypes.add("Select eVTOL Type");
        eVtolTypes.addAll(VehicleSpecs.getModels());

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, eVtolTypes) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                TextView view = (TextView) super.getView(position, convertView, parent);
                if (position == 0) {
                    view.setTextColor(ContextCompat.getColor(getContext(), R.color.light_gray));
                } else {
                    view.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
                }
                return view;
            }

            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, android.view.ViewGroup parent) {
                TextView view = (TextView) super.getDropDownView(position, convertView, parent);
                view.setTextColor(ContextCompat.getColor(getContext(), position == 0 ? R.color.light_gray : R.color.white));
                return view;
            }
        };

        eVtolTypeSpinner.setAdapter(adapter);
        eVtolTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Ensure the selected item text becomes white
                ((TextView) view).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void calculateAndDisplay() {
        try {
            int totalWeight = Integer.parseInt(weightInput.getText().toString());
            int missionRange = 40; // Assuming a fixed mission range of 40 miles.

            MissionRequirement requirement = new MissionRequirement(totalWeight);
            ComparisonEngine engine = new ComparisonEngine();
            VehicleRanking ranking = engine.compareVehicles(requirement, missionRange);

            String eVtolType = eVtolTypeSpinner.getSelectedItem().toString();
            Integer eVtolsRequired = ranking.getRankings().get(eVtolType);

            StringBuilder resultBuilder = new StringBuilder();
            if (eVtolsRequired != null) {
                resultBuilder.append("eVTOLs Required for ").append(eVtolType).append(": ").append(eVtolsRequired).append("\n\n");
                resultBuilder.append("Specifications:\n");
                resultBuilder.append("Payload Capacity: ").append(VehicleSpecs.getPayloadCapacity(eVtolType)).append(" lbs\n");
                resultBuilder.append("Range: ").append(VehicleSpecs.getRange(eVtolType)).append(" miles");
            } else {
                resultBuilder.append(eVtolType).append(" is not suitable for this mission range.");
            }

            resultText.setText(resultBuilder.toString());
        } catch (NumberFormatException e) {
            resultText.setText("Invalid input. Please enter a valid weight.");
        }
    }
}
