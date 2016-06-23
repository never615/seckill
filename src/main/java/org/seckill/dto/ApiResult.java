package org.seckill.dto;

import org.seckill.enums.RequestStateEnum;
import org.seckill.enums.SeckillStateEnum;

/**
 * 封装所有api的请求结果
 * Created by never615 on 6/16/16.
 */
public class ApiResult<T> {

    private int code;
    private String msg;

    public ApiResult(RequestStateEnum requestStateEnum){
        this.code=requestStateEnum.getState();
        this.msg=requestStateEnum.getStateInfo();
    }

    public ApiResult(RequestStateEnum requestStateEnum,String errorMsg){
        this.code=requestStateEnum.getState();
        this.msg=requestStateEnum.getStateInfo()+":"+errorMsg;
    }

    public ApiResult(RequestStateEnum requestStateEnum,T data){
        this.code=requestStateEnum.getState();
        this.msg=requestStateEnum.getStateInfo();
        this.data=data;
    }



    public ApiResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
//
//    public ApiResult(int code, String msg, T data) {
//        this.code = code;
//        this.msg = msg;
//        this.data = data;
//    }

    //    private boolean success;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    //    private String error;
//
//    public ApiResult(boolean success, T data) {
//        this.success = success;
//        this.data = data;
//    }
//
//    public ApiResult(boolean success, String error) {
//        this.success = success;
//        this.error = error;
//    }
//
//    @Override
//    public String toString() {
//        return "ApiResult{" +
//                "success=" + success +
//                ", data=" + data +
//                ", error='" + error + '\'' +
//                '}';
//    }
//
//    public boolean isSuccess() {
//        return success;
//    }
//
//    public void setSuccess(boolean success) {
//        this.success = success;
//    }
//
//    public T getData() {
//        return data;
//    }
//
//    public void setData(T data) {
//        this.data = data;
//    }
//
//    public String getError() {
//        return error;
//    }
//
//    public void setError(String error) {
//        this.error = error;
//    }
}
