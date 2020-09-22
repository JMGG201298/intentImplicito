package com.example.intentimplicito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.net.Proxy.Type.*;
import java.util.Calendar;
import java.util.List;

import static java.net.Proxy.Type.HTTP;

public class MainActivity extends AppCompatActivity {

    Button btnLlamar, btnMapa, btnPagina, btnCorreo, btnCalendario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLlamar=findViewById(R.id.btnLlamar);
        btnMapa=findViewById(R.id.btnMapa);
        btnPagina=findViewById(R.id.btnPaginaWeb);
        btnCorreo=findViewById(R.id.btnEmail);
        btnCalendario=findViewById(R.id.btnCalendario);
    }
    public void llamar(View view){

        Uri numero=Uri.parse("tel:4451117499");
        Intent call=new Intent(Intent.ACTION_DIAL, numero);
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(call,
                PackageManager.MATCH_DEFAULT_ONLY);
        boolean isIntentSafe = activities.size() > 0;
        if(isIntentSafe){
            //Toast.makeText(this,isIntentSafe+"",Toast.LENGTH_LONG);
            startActivity(call);
        }

    }
    public void mapa (View view){
        Uri locacion=Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
        Intent call=new Intent(Intent.ACTION_VIEW, locacion);

        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(call,
                PackageManager.MATCH_DEFAULT_ONLY);
        boolean isIntentSafe = activities.size() > 0;
        if(isIntentSafe){
            //Toast.makeText(this,isIntentSafe+"",Toast.LENGTH_LONG);
            startActivity(call);
        }
    }
    public void pagina (View view){
        Uri webpage = Uri.parse("https://www.android.com");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(webIntent,
                PackageManager.MATCH_DEFAULT_ONLY);
        boolean isIntentSafe = activities.size() > 0;
        if(isIntentSafe){
            //Toast.makeText(this,isIntentSafe+"",Toast.LENGTH_LONG);
            startActivity(webIntent);
        }
    }
    public void correo(View view){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"gugj981220@gmail.com"}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("s17120263@alumnos.itsur.edu.mx"));
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(emailIntent,
                PackageManager.MATCH_DEFAULT_ONLY);
        boolean isIntentSafe = activities.size() > 0;
        if(isIntentSafe){
            //Toast.makeText(this,isIntentSafe+"",Toast.LENGTH_LONG);
            startActivity(emailIntent);
        }

    }
    public void calendario(View view){
        Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2012, 0, 19, 7, 30);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2012, 0, 19, 10, 30);
        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
        calendarIntent.putExtra(CalendarContract.Events.TITLE, "Ninja class");
        calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Secret dojo");
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(calendarIntent,
                PackageManager.MATCH_DEFAULT_ONLY);
        boolean isIntentSafe = activities.size() > 0;
        String title="Selecciona";
        Intent chooser= Intent.createChooser(calendarIntent,title);
        if(isIntentSafe){
            //Toast.makeText(this,isIntentSafe+"",Toast.LENGTH_LONG);
            if(calendarIntent.resolveActivity(getPackageManager())!=null){
                startActivity(chooser);

            }else{
                startActivity(calendarIntent);
            }

        }
    }
}