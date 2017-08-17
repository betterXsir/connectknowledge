package com.hzh.snails.connectknowledge.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mysql.fabric.Server;
import org.springframework.web.bind.annotation.ResponseBody;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> {
    private int status;
    private String msg;
    private T data;

    public ServerResponse(int status){
        this.status = status;
    }

    public ServerResponse(int status, String msg){
        this.status = status;
        this.msg = msg;
    }

    public ServerResponse(int status, T data){
        this.status = status;
        this.data = data;
    }

    public ServerResponse(int status, String msg, T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> ServerResponse<T> createBySuccess(String msg, T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> ServerResponse<T> crateByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode());
    }

    public static <T> ServerResponse<T> createByErrorMessage(String msg){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(), msg);
    }

    public static <T> ServerResponse<T> createByError(T data){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(), data);
    }

    public static <T> ServerResponse<T> createByError(String msg, T data){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(), msg, data);
    }
}
