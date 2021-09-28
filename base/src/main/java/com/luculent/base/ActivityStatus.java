package com.luculent.base;

import java.io.Serializable;

/**
 * @Author byz
 * @CreateData 2020/10/28 9:11
 */
public enum  ActivityStatus  implements Serializable {
    /**
     * onCreate
     */
    CREATE("onCreate"),
    /**
     * onStart
     */
    START("onStart"),
    /**
     * onResume
     */
    RESUME("onResume"),
    /**
     * onPause
     */
    PAUSE("onPause"),
    /**
     * onStop
     */
    STOP("onStop"),
    /**
     * SaveInstanceState
     */
    SAVEINSTANCESTATE("SaveInstanceState"),
    /**
     * onDestroy
     */
    DESTROY("onDestroy");






    String status;

    ActivityStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
