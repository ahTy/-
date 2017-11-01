package bike.com.bike;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class LoginActivity extends AppCompatActivity {

    private EditText name;
    private EditText pw;
    private Button log;
    private TextView reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        name=(EditText)findViewById(R.id.name);
        pw=(EditText)findViewById(R.id.pw);
        log=(Button)findViewById(R.id.log);
        reg=(TextView)findViewById(R.id.reg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent,1);
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("")||pw.getText().toString().equals(""))
                    Toast.makeText(LoginActivity.this,"用户名或密码不能为空",Toast.LENGTH_LONG).show();
                else{
                    Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode)
        {
            case 1:if(resultCode==RESULT_OK){
                String rname=data.getStringExtra("name");
                String rpw=data.getStringExtra("pw");
                name.setText(rname);
                pw.setText(rpw);
            }
                break;
            default:
        }
    }
}
