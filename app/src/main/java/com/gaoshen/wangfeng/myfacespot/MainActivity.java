package com.gaoshen.wangfeng.myfacespot;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facepp.error.FaceppParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Bitmap bm = null;

    private static final int PICK_CODE = 0X120;
    private ImageView mPhoto;//Image View
    private Button mGetImage;//getImage Button
    private Button mDetect;// Detect Button
    private TextView mTip; //TextView
    private View mWaitting; //FrameLayout 进度条

    private String mCurrentPhotoStr;//存储图片的路径

    private Bitmap mPhotoImg;
    private Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        mPaint = new Paint();

    }


    /**
     * 初始化View
     */
    private void initViews() {
        mPhoto = (ImageView) findViewById(R.id.img);
        mGetImage = (Button) findViewById(R.id.getImage);
        mDetect = (Button) findViewById(R.id.detect);
        mTip = (TextView) findViewById(R.id.tip);
        mWaitting = findViewById(R.id.id_waiting);

        mGetImage.setOnClickListener(this);
        mDetect.setOnClickListener(this);
    }


    private static final int MSG_SUCESS = 0X111;
    private static final int MSG_ERROR = 0X121;
    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SUCESS:
                    mWaitting.setVisibility(View.GONE);
                    JSONObject rs = (JSONObject) msg.obj;
                    prepareRsBitmaap(rs);
                    mPhoto.setImageBitmap(mPhotoImg);
                    break;
                case MSG_ERROR:
                    mWaitting.setVisibility(View.GONE);
                    String error = (String) msg.obj;
                    if (TextUtils.isEmpty(error)) {
                        mTip.setText("Error");
                    } else {
                        mTip.setText(error);
                    }
                    break;
            }
        }
    };

    /**
     * @param rs
     */
    private void prepareRsBitmaap(JSONObject rs) {

        Bitmap bitmap = Bitmap.createBitmap(mPhotoImg.getWidth(), mPhotoImg.getHeight(), mPhotoImg.getConfig());
        Canvas canva = new Canvas(bitmap);
        canva.drawBitmap(mPhotoImg, 0, 0, null);

        try {
            //得到有多少张脸
            JSONArray faces = rs.getJSONArray("face");
            int faceCount = faces.length();
            mTip.setText("find" + faceCount);//多少张
            for (int i = 0; i < faceCount; i++) {
                JSONObject face = faces.getJSONObject(i);
                JSONObject posObj = face.getJSONObject("position");
                float x = (float) posObj.getJSONObject("center").getDouble("x");
                float y = (float) posObj.getJSONObject("center").getDouble("y");

                float width = (float) posObj.getDouble("width");
                float height = (float) posObj.getDouble("height");

                //将百分比  换成 实际像素的一个值
                x = x / 100 * bitmap.getWidth();//中点处x轴的位置
                y = y / 100 * bitmap.getHeight();//中点处y轴的位置
                width = width / 100 * bitmap.getWidth();//中点处x轴的位置
                height = height / 100 * bitmap.getHeight();//中点处x轴的位置

                //画脸部区域的一个盒子

                //线的颜色
                mPaint.setColor(0xffffffff);
                mPaint.setStrokeWidth(3);//线的宽度
                //左侧竖线
                canva.drawLine(x - width / 2, y - height / 2, x - width / 2, y + height / 2, mPaint);
                //上面横线
                canva.drawLine(x - width / 2, y - height / 2, x + width / 2, y - height / 2, mPaint);
                //右侧竖线
                canva.drawLine(x + width / 2, y - height / 2, x + width / 2, y + height / 2, mPaint);
                //下面横线
                canva.drawLine(x - width / 2, y + height / 2, x + width / 2, y + height / 2, mPaint);

                //获取人的 年龄 性别
                int age = face.getJSONObject("attribute").getJSONObject("age").getInt("value");
                String gender = face.getJSONObject("attribute").getJSONObject("gender").getString("value");

                //将TextView 转换为Bitmap
                Bitmap ageBitmap = buildAgeBitmap(age, "Male".equals(gender));
                int ageWidth = ageBitmap.getWidth();
                int ageHeight = ageBitmap.getHeight();
                if (bitmap.getWidth() < mPhoto.getWidth() && bitmap.getHeight() < mPhoto.getHeight()) {
                    float ratio = Math.max(bitmap.getWidth() * 1.0F / mPhoto.getWidth(), bitmap.getHeight() * 1.0F / mPhoto.getHeight());
                    ageBitmap = Bitmap.createScaledBitmap(ageBitmap, (int) (ageWidth * ratio), (int) (ageHeight * ratio), false);
                }
                canva.drawBitmap(ageBitmap, x - ageBitmap.getWidth() / 2, y - height / 2 - ageBitmap.getHeight(), null);
                mPhotoImg = bitmap;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Bitmap buildAgeBitmap(int age, boolean isMale) {

        TextView tv = (TextView) mWaitting.findViewById(R.id.age_and_gender);
        tv.setText(age + "");
        if (isMale) {
            tv.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.male), null, null, null);
        } else {
            tv.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.female), null, null, null);
        }
        tv.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(tv.getDrawingCache());
        tv.destroyDrawingCache();
        return bitmap;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.getImage:
                //获取图片操作
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                //选择图片类型
                /**
                 * MediaStore.Images.Media.EXTERNAL_CONTENT_URI 获取相册中所有的图
                 */
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, PICK_CODE);
                break;
            case R.id.detect:

                //显示进度条
                mWaitting.setVisibility(View.VISIBLE);

                if (mCurrentPhotoStr != null && !mCurrentPhotoStr.trim().equals("")) {
                    resizePhoto();
                } else {
                    mPhotoImg = BitmapFactory.decodeResource(getResources(), R.drawable.t4);

                }

                FaceDetect.detect(mPhotoImg, new FaceDetect.CallBack() {
                    @Override
                    public void success(JSONObject result) {

                        Message mes = Message.obtain();
                        mes.what = MSG_SUCESS;
                        mes.obj = result;
                        mHandle.sendMessage(mes);
                    }

                    @Override
                    public void error(FaceppParseException exception) {

                        Message mes = Message.obtain();
                        mes.what = MSG_ERROR;
                        mes.obj = exception;
                        mHandle.sendMessage(mes);
                    }
                });
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == PICK_CODE) {
            Log.e("onActivityResult", "--data--" + data);
            if (data != null) {

                Uri uri = data.getData();
                Log.e("===URI===", "" + uri);//    content://media/external/images/media/37282

                //多媒体数据库的接口，Intent返回 的uri路径 第二个参数，
                /**
                 * 这个MediaStore包括了多媒体数据库的所有信息，包括音频，视频和图像,android把所有的多媒体数据库接口进行了封装，
                 * 所有的数据库不用自己进行创建，直接调用利用ContentResolver去掉用那些封装好的接口就可以进行数据库的操作了。
                 */
                Cursor cursor = this.getContentResolver().query(uri, null, null, null, null);
                cursor.moveToFirst();
                //获得用户选着图片的索引值
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                //通过下标 获取图片路径
                mCurrentPhotoStr = cursor.getString(idx);//  /storage/sdcard0/DCIM/100MEDIA/IMAG1058.jpg
                Log.e("@@@---mCurrentPhotoStr", mCurrentPhotoStr);
                cursor.close();
                //压缩图片
                resizePhoto();
                mPhoto.setImageBitmap(mPhotoImg);
                mTip.setText("Click Detect ==>");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void resizePhoto() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //设置为true时，自加载图片的宽高，不会返回一个图片
        options.inJustDecodeBounds = true;
        //options 存放图片宽高的值
        Bitmap bmd = BitmapFactory.decodeFile(mCurrentPhotoStr, options);
        int widht = options.outWidth;
        int hright = options.outHeight;
        Log.e("图片的宽高", widht + "--" + hright);
        double ratio = Math.max(widht * 1.0d / 1024f, hright * 1.0d / 1024f);
        options.inSampleSize = (int) Math.ceil(ratio);

        //设置为false才会加载图片
        options.inJustDecodeBounds = false;
        mPhotoImg = BitmapFactory.decodeFile(mCurrentPhotoStr, options);

    }


}
