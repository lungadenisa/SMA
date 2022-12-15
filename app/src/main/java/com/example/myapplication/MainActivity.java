package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private EditText eName;
    private Button bClick;
    private TextView tName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText eName = (EditText) findViewById(R.id.eName);
        Button bClick = (Button) findViewById(R.id.bClick);
        TextView tName = (TextView) findViewById(R.id.tName);

        FloatingActionButton buttonShare = (FloatingActionButton) findViewById(R.id.shareButton);
        FloatingActionButton buttonSearch = (FloatingActionButton) findViewById(R.id.searchButton);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.colors, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        bClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String afisare = "Hello, " + eName.getText() + "!"; //luam un string in care stocam ce vrem sa afisam.
                tName.setText(afisare); //afisarea stringului
                builder.setMessage(afisare); //setam mesajul pentru popup-window
                builder.setCancelable(true);
                builder.setPositiveButton(":)", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Have a nice day", Toast.LENGTH_SHORT).show();//afisarea mesajului cu ajutorul metodei Toast, length short reprezinta durata perioadei in care este afisat mesajul.
                    }
                }); // cream un buton pentru situatia pozitiva
                builder.setNegativeButton(":(", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Be happy ", Toast.LENGTH_SHORT).show();
                    }
                }); // cream un buton pentru situatia negativa
                AlertDialog alert = builder.create(); //declaram o alerta
                alert.show();//afisarea alertei
            }
        });

        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String name = tName.getText().toString();
                shareIntent.putExtra(Intent.EXTRA_TEXT, name);
                startActivity(Intent.createChooser(shareIntent, "Share using: "));
            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(Intent.ACTION_VIEW);
                String text = tName.getText().toString();
                String url = "https://www.google.ro/search?q=" + text;
                searchIntent.setDataAndType(Uri.parse(url), "text/plain");
                startActivity(searchIntent);
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String colour = spinner.getItemAtPosition(i).toString();
                switch (colour) {
                    case "Pink":
                        bClick.setBackgroundColor(Color.rgb(255, 0, 193));
                        break;
                    case "Blue":
                        bClick.setBackgroundColor(Color.rgb(0,58,255));
                        break;
                    case "Green":
                        bClick.setBackgroundColor(Color.rgb(37,197,108));
                        break;
                    case "Black":
                        bClick.setBackgroundColor(Color.rgb(0,0,0));
                        break;
                    case "White":
                        bClick.setBackgroundColor(Color.rgb(255,255,255));
                        break;
                    case "Orange":
                        bClick.setBackgroundColor(Color.rgb(255,173,0));
                        break;
                    case "Red":
                        bClick.setBackgroundColor(Color.rgb(255, 0, 0));
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
}