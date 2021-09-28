package com.luculent.base.util;

import android.os.Parcelable;

import com.tencent.mmkv.MMKV;

import java.util.Set;

/**
 * @Author byz
 * @CreateData 2020/10/28 9:33
 * @blame Android Team
 * MMKV 工具类 用于替换Sp
 */
public class MmkvUtil {
    private MmkvUtil(){}
    private static MmkvUtil mMmkvUtil;
    MMKV kv;
    public static MmkvUtil getMmkvUtil(){
        if(mMmkvUtil == null){
            synchronized (MmkvUtil.class){
                if(mMmkvUtil == null){
                    mMmkvUtil = new MmkvUtil();
                }
            }
        }
        return mMmkvUtil;
    }

     public void setting(MMKV kv){
        this.kv = kv;
    }

    public void putStringData(String key,String value){
        kv.encode(key,value);
    }

    public String getStringData(String key){
        return kv.decodeString(key);
    }

    public void putIntData(String key,int value){
        kv.encode(key,value);
    }

    public int getIntData(String key){
        return kv.decodeInt(key);
    }

    public void putBooleanData(String key,boolean value){
        kv.encode(key,value);
    }

    public boolean getBooleanData(String key,boolean defaultValue){
        return kv.decodeBool(key,defaultValue);
    }

    public void putLongData(String key,long value){
        kv.encode(key,value);
    }

    public long getLongData(String key,long defaultValue){
        return kv.decodeLong(key,defaultValue);
    }

    public void putFloatData(String key,float value){
        kv.encode(key,value);
    }

    public float getFloatData(String key,float value){
        return kv.decodeFloat(key,value);
    }

    public void putBytesData(String key,byte[] value){
        kv.encode(key,value);
    }

    public byte[] getBytesData(String key,byte[] defaultValue){
        return kv.decodeBytes(key,defaultValue);
    }

    public void putDoubleData(String key,double value) {
        kv.encode(key,value);
    }

    public double getDoubleData(String key, double defaultValue){
        return kv.decodeDouble(key,defaultValue);
    }

    public void putPecodeParcelableData(String key,Parcelable value){
        kv.encode(key,value);
    }

    public Parcelable getPecodeParcelableData(String key,Parcelable defaultValue){
        return kv.decodeParcelable(key,defaultValue.getClass());
    }


    public void putStringSetData(String key,Set<String> value){
        kv.encode(key,value);
    }

    public Set<String>  getStringSetData(String key,Set<String> defaultValue){
        return kv.decodeStringSet(key,defaultValue);
    }

}
