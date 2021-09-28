package com.luculent.base.http;

/**
 * @Author byz
 * @CreateData 2021/1/11 9:48
 * @blame Android Team
 * @Content
 */
public abstract class BaseCallBackListener<T>  {
   public abstract void onCallBack(String code,T t);
   public abstract void onException(Throwable e);
}
