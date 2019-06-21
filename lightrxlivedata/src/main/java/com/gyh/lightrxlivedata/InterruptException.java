package com.gyh.lightrxlivedata;

/**
 * Created by  yahuigao
 * Date: 2019-05-11
 * Time: 17:27
 * Description:
 */
public class InterruptException extends Exception {

    private Object data;

    public InterruptException(String detailMessage, Object data) {
        super(detailMessage);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
