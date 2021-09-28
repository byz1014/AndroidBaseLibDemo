package com.luculent.base;

import android.os.Bundle;
import android.os.MessageQueue;
import android.os.MessageQueue.IdleHandler;

/**
 * @Author byz
 * @CreateData 2020/12/14 10:04
 * @blame Android Team
 * @Content
 */
public class BaseMessageQueueHandler implements IdleHandler {
   private Bundle savedInstanceState;
   private MessageQueueListener messageQueueListener;

    public BaseMessageQueueHandler(Bundle savedInstanceState, MessageQueueListener messageQueueListener) {
        this.savedInstanceState = savedInstanceState;
        this.messageQueueListener = messageQueueListener;
    }

    @Override
    public boolean queueIdle() {
        if(messageQueueListener!=null){
          return messageQueueListener.onQueueIdle(savedInstanceState);
        }
        return false;
    }






    public interface MessageQueueListener{
        public boolean onQueueIdle(Bundle savedInstanceState);
    }


}
