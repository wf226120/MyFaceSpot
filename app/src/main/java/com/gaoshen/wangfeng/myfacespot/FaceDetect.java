package com.gaoshen.wangfeng.myfacespot;

import android.graphics.Bitmap;
import android.util.Log;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

/**
 * Created by Administrator on 2016/9/5.
 */
public class FaceDetect {

    public interface CallBack{
        //成功返回json
        void success(JSONObject result);
        //失败error
        void error(FaceppParseException exception);
    }

    /**
     * 生成final 内部要使用他
     * @param bm
     * @param callBack
     */
    public static void detect(final Bitmap bm,final CallBack callBack){

        /**
         *耗时操作，使用线程
         */
        new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    // 创建请求
                    HttpRequests requests=new HttpRequests(Constant.KEY,Constant.SECRET,true,true);
                    //创建一个Bitmap  对Bitmap进行一些操作
                    Bitmap bmSmall=Bitmap.createBitmap(bm,0,0,bm.getWidth(),bm.getHeight());
                    //创建字节流 将Bitmap压缩到 字节中去
                    ByteArrayOutputStream stream=new ByteArrayOutputStream();
                    //将Bitmap压缩到 stream中去
                    bmSmall.compress(Bitmap.CompressFormat.JPEG,100,stream);
                    //返回一个字节数组
                    byte []arrays=stream.toByteArray();
                    //
                    PostParameters postParameter=new PostParameters();
                    postParameter.setImg(arrays);
                    //将postParameter传入  返回一个JSONObject
                    JSONObject jsonObject= requests.detectionDetect(postParameter);

                    Log.e( "====run: ",jsonObject.toString() );
                    //到此没有错误 判断
                    if(callBack!=null){
                        //没有错误传一个需要处理的数据
                        callBack.success(jsonObject);
                    }
                } catch (FaceppParseException e) {
                    e.printStackTrace();
                    if(callBack!=null){
                        callBack.error(e);
                    }
                }
            }
        }).start();
    }
}
