package com.system.VO;

import lombok.Data;

import static com.system.VO.Constants.*;

@Data
public class DataVO<T> {
    private Integer code;   //返回码，0成功
    private String msg;     //返回描述
    private T data;         //返回数据
    private Long count;     //分页查询总记录数

    private DataVO(){}

    public DataVO(Integer code, String msg, T data, Long count) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }
    //登陆成功返回信息
    public static DataVO<Object> success(){
        return new DataVO(0,OK_MSG,null,null);
    }
    //登录失败返回信息
    public static DataVO<Object> fail(){
        return new DataVO(-1,FAIL_MSG,null,null);
    }
    public static DataVO<Object> fail(String msg){
        return new DataVO(-1,msg,null,null);
    }
}
