package com.example.ahmedetman.peopleapitask.views;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import com.example.ahmedetman.peopleapitask.R;
import com.example.ahmedetman.peopleapitask.models.CharacterItem;

public class DetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        CharacterItem item = (CharacterItem) getIntent().getSerializableExtra(MainActivity.KEY_ITEM);
        TextView textView = findViewById(R.id.tv_vehicles);
        if(item != null && item.getVehicles() != null && item.getVehicles().size() != 0){
            StringBuilder s = new StringBuilder();;
            for (String v :
                    item.getVehicles()) {
                s.append(v + "\n");
            }
            textView.setText(s);
        }else{
            textView.setText("no vehicles");
        }

    }

}
