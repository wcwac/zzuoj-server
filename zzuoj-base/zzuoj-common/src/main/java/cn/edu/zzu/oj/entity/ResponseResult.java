package cn.edu.zzu.oj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult extends BaseDTO {
    /**
     * 返回状态码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;

    public static ResponseResult ok(String msg, Object temp) {
        return new ResponseResult(1, msg, temp);
    }
    public static ResponseResult err(String msg, Object temp) {
        return new ResponseResult(0, msg, temp);
    }
}
