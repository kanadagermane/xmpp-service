package net.gotev.xmppservice;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import net.gotev.xmppservice.database.SqLiteDatabase;

import org.jxmpp.jid.impl.JidCreate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        XmppAccount account = new XmppAccount();

        try {
            account.setXmppJid(JidCreate.bareFrom("daniel@localhost"));
        } catch (Exception ex) {Log.d("xmppAccount", "jid");};

        account.setHostAdress("10.0.2.2");
        account.setPassword("12345");
        account.setPort(5222);
        account.setResourceName("android");
        account.setServiceName("localhost");

       /*//XmppService service = new XmppService();

        XmppServiceCommand.connect(this, account);
*/

        XmppServiceBroadcastEventEmitter.initialize(this, "xmpp-service");
        SqLiteDatabase database = new SqLiteDatabase(this, "datenbank");


        XmppServiceConnection connection = new XmppServiceConnection(account,database);

        try {
            connection.connect();
        } catch (Exception ey ) {
            Log.d("jo", ey.getMessage());
        }
    }

    public  void xmppLoginFinished(boolean result) {
        Toast.makeText(this, "hey" , Toast.LENGTH_LONG).show();
    }
}
