package bike.com.bike;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText name;
    private EditText pw;
    private Button sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        name=(EditText)findViewById(R.id.name);
        pw=(EditText)findViewById(R.id.pw);
        sign=(Button)findViewById(R.id.sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("name",name.getText().toString());
                intent.putExtra("pw",pw.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
