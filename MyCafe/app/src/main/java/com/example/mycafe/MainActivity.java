package com.example.mycafe;

import static com.google.android.material.internal.ViewUtils.getChildren;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.mycafe.ui.order.GalleryFragment;
import com.example.mycafe.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private FragmentManager FragmentManager;

    //notify
    private static final String ACTION_UPDATE_NOTIFICATION =
            "com.example.android.notifyme.ACTION_UPDATE_NOTIFICATION";
    private NotificationReceiver mReceiver = new NotificationReceiver();
    private ImageButton button_notify;
    private ImageButton button_cancel;
    private ImageButton button_update;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private NotificationManager mNotifyManager;
    private static final int NOTIFICATION_ID = 0;
    //notify

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                // The intent does not have a URI, so declare the "text/plain" MIME type
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"C109118142@nkust.edu.tw"}); // 收件人
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");
                emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
                // You can also attach multiple items by passing an ArrayList of Uris
                startActivity(emailIntent);

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Toast.makeText(view.getContext(), "FAB onClick！", Toast.LENGTH_LONG).show();
            }
        });

        //notify
        registerReceiver(mReceiver,new IntentFilter(ACTION_UPDATE_NOTIFICATION));
        button_notify = findViewById(R.id.btn_notify);
        button_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });

        button_update = findViewById(R.id.btn_noadd);
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNotification();
            }
        });

        button_cancel = findViewById(R.id.btn_nocancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelNotification();
            }
        });
        //notify

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_about,R.id.nav_card, R.id.nav_shop, R.id.nav_money,R.id.nav_record,R.id.nav_post,R.id.nav_search,R.id.nav_mail,R.id.nav_map)
                .setOpenableLayout(drawer)
                .build();
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);



//notify
        createNotificationChannel();//不加這行會當掉
        setNotificationButtonState(true, false, false);


    }



    public void btn_onClick(View view) {
        switch (view.getId()) {
            /*case R.id.moneybtn: {
                Toast.makeText(getApplicationContext(), "moneybtn onClick！", Toast.LENGTH_LONG).show();
                showMoneyDialog(view);
                break;
            }*/
            case R.id.btn_search:{
                Uri uri=Uri.parse("https://www.facebook.com/profile.php?id=100001342954947");
                Intent i=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
                break;
            }
        }
    }

    private int parseInt(String text) {
        int result=0;
        try {
            result = Integer.parseInt(text);
        } catch (Exception e) {
        }
        return result;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Intent intent = new Intent();

        //FragmentManager = getSupportFragmentManager();
        //SlideshowFragment = new SlideshowFragment();

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.nav_search:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(this, "Search", Toast.LENGTH_LONG).show();

                Uri uri = Uri.parse("https://www.facebook.com/profile.php?id=100001342954947");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

                //NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment_content_main);
                //return NavigationUI.onNavDestinationSelected(item,navController) || super.onOptionsItemSelected(item);

            case R.id.nav_mail:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(this, "mail", Toast.LENGTH_LONG).show();

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                // The intent does not have a URI, so declare the "text/plain" MIME type
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"C109118142@nkust.edu.tw"}); // 收件人
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");
                emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
                // You can also attach multiple items by passing an ArrayList of Uris
                startActivity(emailIntent);

                //NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment_content_main);
                //return NavigationUI.onNavDestinationSelected(item,navController) || super.onOptionsItemSelected(item);


            case R.id.nav_card:
                NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment_content_main);
                return NavigationUI.onNavDestinationSelected(item,navController) || super.onOptionsItemSelected(item);
                //break;

            case R.id.nav_record: { //點擊後連結到個人消費紀錄
                Toast.makeText(this, "消費紀錄查詢", Toast.LENGTH_LONG).show();

                Intent cost = new Intent(MainActivity.this, cost.class);
                startActivity(cost);
                break;
            }

            case R.id.nav_money: { //點擊後連結到儲值畫面
                Toast.makeText(this, "儲值", Toast.LENGTH_LONG).show();

                Intent deposit = new Intent(MainActivity.this, deposit.class);
                startActivity(deposit);
                break;
            }
            case R.id.nav_order: {
                Toast.makeText(this, "購物車", Toast.LENGTH_LONG).show();

                Intent shop = new Intent(MainActivity.this, shopping.class);//點擊後連結到購物車
                startActivity(shop);
                break;
            }
            case R.id.nav_signout: {
                Toast.makeText(this, "已登出", Toast.LENGTH_LONG).show();

                Intent sign = new Intent(MainActivity.this, LoginActivity.class);//點擊後連結到購物車
                startActivity(sign);
                break;
            }
//            case R.id.nav_box: {
//                Toast.makeText(this, "意見箱", Toast.LENGTH_LONG).show();
//
//                Intent box = new Intent(MainActivity.this, Box.class);//點擊後連結到購物車
//                startActivity(box);
//                break;
//            }
            case R.id.nav_power: {
                //Toast.makeText(this, "意見箱", Toast.LENGTH_LONG).show();

                Intent power = new Intent(MainActivity.this, Power.class);//點擊後連結到購物車
                startActivity(power);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void showMoneyDialog ( View view ){
        MoneyDialogFragment moneyDialogFragment = new MoneyDialogFragment();
        moneyDialogFragment.show(getSupportFragmentManager() ,"MoneyDialog");
    }

    //notify
    void setNotificationButtonState(Boolean isNotifyEnabled,
                                    Boolean isUpdateEnabled,
                                    Boolean isCancelEnabled) {
        button_notify.setEnabled(isNotifyEnabled);
        button_update.setEnabled(isUpdateEnabled);
        button_cancel.setEnabled(isCancelEnabled);

    }

    public void sendNotification() {
        Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
        PendingIntent updatePendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        //notifyBuilder.addAction(R.drawable.ic_update, "update !" , updatePendingIntent);
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
        setNotificationButtonState(false, true, true);

    }

    public void createNotificationChannel()
    {
        mNotifyManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {
            // Create a NotificationChannel
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Mascot Notification", NotificationManager
                    .IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Mascot");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

    private NotificationCompat.Builder getNotificationBuilder(){
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setContentTitle("Notify!")
                .setContentText("通知你")
                .setSmallIcon(R.drawable.logo)
                .setContentIntent(notificationPendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);//設定顏色可以閃閃閃
        return notifyBuilder;
    }

    public void updateNotification() {
        Bitmap androidImage = BitmapFactory
                .decodeResource(getResources(),R.drawable.airplane_1);
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        notifyBuilder.setStyle(new NotificationCompat.BigPictureStyle()
                .bigPicture(androidImage)
                .setBigContentTitle("更新啦!"));
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
        setNotificationButtonState(false, false, true);
    }

    public void cancelNotification() {
        mNotifyManager.cancel(NOTIFICATION_ID);//送出的訊息 刪掉
        setNotificationButtonState(true, false, false);

    }
    public class NotificationReceiver extends BroadcastReceiver {

        public NotificationReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            updateNotification();        }
    }
    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }
    //notify

    private void recordImageView() {
        String id = "Image ID";
        String name = "Image Title";

        // [START image_view_event]
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        // [END image_view_event]
    }

}

