package mikeperibonio.com.divesupervisor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends Activity {
    Context context = this;
    CheckBox meters;
    String metersString;
    Button surface;
    Button water;
    Editor editor;

    class C01191 implements OnClickListener {
        C01191() {
        }

        public void onClick(View v) {
            if (((CheckBox) v).isChecked()) {
                editor = context.getSharedPreferences("your_file_name", 0).edit();
                editor.putString("yourStringName", "meters");
                editor.commit();
                return;
            }
            editor = MainActivity.this.getSharedPreferences("your_file_name", 0).edit();
            editor.putString("yourStringName", "feet");
            editor.commit();
        }
    }

    class C01202 implements OnClickListener {
        C01202() {
        }

        public void onClick(View view) {
            Intent k = new Intent(MainActivity.this, Diving.class);
            k.putExtra("m", MainActivity.this.metersString);
            MainActivity.this.startActivity(k);
        }
    }

    class C01213 implements OnClickListener {
        C01213() {
        }

        public void onClick(View view) {
            MainActivity.this.startActivity(new Intent(MainActivity.this, Chamber.class));
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.water = (Button) findViewById(R.id.button1);
        this.surface = (Button) findViewById(R.id.button2);
        String string = getSharedPreferences("your_file_name", 0).getString("yourStringName", "default_value_here_if_string_is_missing");
        this.meters = (CheckBox) findViewById(R.id.checkBox);
        if (string.equals("meters")) {
            this.meters.setChecked(true);
        }
        addwaterListener();
        addsurfaceListener();
        addListenerOnChkIos();
    }

    public void addListenerOnChkIos() {
        this.meters.setOnClickListener(new C01191());
    }

    public void show(String x) {
        Toast.makeText(getApplicationContext(), x, 1).show();
    }

    public void addwaterListener() {
        this.water.setOnClickListener(new C01202());
    }

    public void addsurfaceListener() {
        this.surface.setOnClickListener(new C01213());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private String readFromFile() {
        String retrievedString = "";
        try {
            InputStream inputStream = openFileInput("metersFile");
            if (inputStream == null) {
                return retrievedString;
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String tempString = "";
            StringBuilder stringBuilder = new StringBuilder();
            while (true) {
                tempString = bufferedReader.readLine();
                if (tempString == null) {
                    inputStream.close();
                    return stringBuilder.toString();
                }
                stringBuilder.append(tempString);
            }
        } catch (FileNotFoundException e) {
            return retrievedString;
        } catch (IOException e2) {
            return retrievedString;
        }
    }
}

