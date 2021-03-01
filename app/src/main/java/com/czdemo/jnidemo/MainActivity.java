package com.czdemo.jnidemo;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amt.cip.Identify;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity   implements View.OnClickListener{

/**
 *如果雄心得到尊敬.他的回报是-财富， 荣誉，得以控制的命运-。
 **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPower();
        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
         tv.setText(  Identify.getIdentifyInstance().getAntennaSum(16)+":<====");
        Identify.getIdentifyInstance().getInfo();
        initView();
        initListener();
    }





    @Override
    protected void onStart() {
        super.onStart();
        // 绑定服务
        bindService(new Intent(this, MyService5.class), connection, BIND_AUTO_CREATE);
    }

    private Button btPlayMusic;
    private Button btPausedContinue;
    private Button btStop;

    private void initView() {
        btPlayMusic = (Button) findViewById(R.id.bt_play_music);
        btPausedContinue = (Button) findViewById(R.id.bt_paused_continue);
        btStop = (Button) findViewById(R.id.bt_stop);
    }

    private void initListener() {
        btPlayMusic.setOnClickListener(this);
        btPausedContinue.setOnClickListener(this);
        btStop.setOnClickListener(this);
    }

    private IMusicPlay iMusicPlay;

    private ServiceConnection connection = new ServiceConnection() {
        /**
         * 连接到服务
         * @param name
         * @param service
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iMusicPlay = (IMusicPlay) service;
        }

        /**
         * 断开连接
         * @param name   9
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 解绑服务：注意bindService后 必须要解绑服务，否则会报 连接资源异常
        if (null != connection) {
            unbindService(connection);
        }
    }

    // 音乐文件到路径
    private final String MUSIC_PATH ="http://192.168.2.40:9000/test.mp3";

    @Override
    public void onClick(View v) {

        if (iMusicPlay == null) {
            Toast.makeText(this, "音乐播放服务连接失败", Toast.LENGTH_LONG).show();
            return;
        }

        switch (v.getId()) {

            case R.id.bt_play_music:
                iMusicPlay.playMusic(MUSIC_PATH);
                break;

            case R.id.bt_paused_continue:
                if ("暂停播放".equals(btPausedContinue.getText().toString())) {
                    btPausedContinue.setText("继续播放");
                    iMusicPlay.pausedPlay();
                } else {
                    btPausedContinue.setText("暂停播放");
                    iMusicPlay.continuePlay();
                }
                break;

            case R.id.bt_stop:
                iMusicPlay.stopPlay();
                break;

            default:
                break;
        }
    }

    /**
     * 用户按返回键，系统会调用到此方法
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);

        // 判断是否在播放，如果在播放中，才告知用户 弹出对话框
        if (iMusicPlay == null) {
            return true;
        }

        MediaPlayer mediaPlayer = iMusicPlay.getMediaPlayer();

        if(mediaPlayer.isPlaying()) {

            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    showAlertDialog();
                    break;
            }
        }
        return true;
    }

    /**
     * 弹出对话框
     */
    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("提示");
        builder.setMessage("确定要关闭音乐播放？");
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNeutralButton("后台播放", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 既然是后台播放，就是要把当前Activity切换到后台
                moveTaskToBack(true);
            }
        });
        builder.setPositiveButton("取消", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void requestPower() {
        //判断是否已经赋予权限
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PERMISSION_GRANTED&&
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PERMISSION_GRANTED) {
            //申请权限，字符串数组内是一个或多个要申请的权限，1是申请权限结果的返回参数，在onRequestPermissionsResult可以得知申请结果
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,}, 1);
        }
    }
}

