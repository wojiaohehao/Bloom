package com.xzzb.bloom.presenters;

import android.util.Base64;
import android.util.Log;

import com.tencent.ilivesdk.ILiveSDK;
import com.tencent.ilivesdk.core.ILiveLog;
import com.xzzb.bloom.model.RecordInfo;
import com.xzzb.bloom.presenters.viewinface.RecListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xkazerzhang on 2016/12/22.
 */
public class RecListViewHelper {
    private final static String TAG = "RecListViewHelper";

    private RecListView recView;
    private Random mRand = new Random();

    public RecListViewHelper(RecListView view){
        recView = view;
    }

    /**
     * 计算SHA值
     * @param strSource 源字符串
     * @return
     * @throws Exception
     */
    public String getHmacSHA1(String secretKey, String strSource) throws Exception{
        Mac mac = Mac.getInstance("HmacSHA1");
        SecretKeySpec secret = new SecretKeySpec(secretKey.getBytes("UTF-8"), mac.getAlgorithm());
        mac.init(secret);

        byte[] sh1bytes = mac.doFinal(strSource.getBytes("UTF-8"));
        return Base64.encodeToString(sh1bytes, Base64.NO_WRAP);     // 避免自动添加换行符
    }

    public String getSignature(String secretKey, String path){
        String srcStr = "GET";
        try {
            int start = path.indexOf("//") + 2;
            int pos = path.indexOf("?") + 1;
            if (start < 2)
                start = 0;
            if (pos < 1)
                return "";
            // 添加路径
            srcStr += path.substring(start, pos);
            // 分割参数
            List<String> params = Arrays.asList(path.substring(pos).split("&"));
            Comparator<String> comparator = new Comparator<String>() {
                @Override
                public int compare(String lhs, String rhs) {
                    return lhs.compareTo(rhs);
                }
            };
            Collections.sort(params, comparator);
            for (String param : params) {
                srcStr += (param + '&');
            }
            srcStr = srcStr.substring(0, srcStr.length() - 1);
            ILiveLog.d(TAG, "getSignature->src:" + srcStr);
            return getHmacSHA1(secretKey, srcStr);
        }catch (Exception e){
            ILiveLog.e(TAG, "getSignature->error:"+e.toString());
            return "";
        }
    }

    public void refresh(){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(8, TimeUnit.SECONDS)
                .readTimeout(8, TimeUnit.SECONDS)
                .build();
        String path = "https://vod.api.qcloud.com/v2/index.php?Action=DescribeVodPlayInfo&Region=gz";
        path += "&Nonce="+mRand.nextInt(10000);
        path += "&SecretId=AKIDlnkbPqucPuUgJmkMnaocUEBhZzBa5bpO";
        path += "&orderby=1";
        path += "&fileName=sxb";
        path += ("&Timestamp="+String.valueOf(System.currentTimeMillis()/1000));

        path = path + "&Signature="+ URLEncoder.encode(getSignature("yw2nqIhlWkCmw7xZQaHUITMspCkatqsU", path));
        Log.v(TAG, "onResponse->req: "+path);
        Request request = new Request.Builder()
                .url(path)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure->err: "+e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                parseRespnose(response.body().string());
            }
        });
    }

    private void parseRespnose(String strRsp){
        Log.v(TAG, "parseRespnose->ret: "+strRsp);
        try {
            final ArrayList<RecordInfo> listRecs = new ArrayList<>();
            JSONObject jsonRoot = new JSONObject(strRsp);
            int errCode = jsonRoot.getInt("code");
            JSONArray jsonFileSets = jsonRoot.getJSONArray("fileSet");
            for (int i=0; i<jsonFileSets.length(); i++){
                JSONObject jsonFile = jsonFileSets.getJSONObject(i);
                listRecs.add(new RecordInfo(jsonFile));
            }
            ILiveSDK.getInstance().runOnMainThread(new Runnable() {
                @Override
                public void run() {
                    recView.onUpdateRecordList(listRecs);
                }
            }, 0);

        }catch (Exception e){
            Log.e(TAG, "parseRespnose->error: "+e.toString());
        }

    }
}
