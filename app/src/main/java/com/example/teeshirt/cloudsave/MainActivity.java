package com.example.teeshirt.cloudsave;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.*;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;

import java.net.MalformedURLException;

public class MainActivity extends ActionBarActivity {
    private MobileServiceClient mClient;
    private MobileServiceTable<Mobitext> mTable = mClient.getTable("mobitext", Mobitext.class);
    private EditText et1;
    private Button btn;
    private Mobitext mobitext = new Mobitext();
    private String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            mClient = new MobileServiceClient( "https://bargain.azure-mobile.net/", "bDUuDEeQGzcjKSlKKoscSyiqpNFfIo33", this );

        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        //addRecord();

    }

    public void btnClick(){
        btn = (Button)findViewById(R.id.btn);
        et1 = (EditText)findViewById(R.id.et1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 text = et1.getText().toString();
            }
        });

    }

    public void addRecord(){

        mobitext.Word = text;
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    mTable.insert(mobitext).get();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
}
