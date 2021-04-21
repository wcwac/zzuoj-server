package cn.edu.zzu.oj.Exceptions;

import cn.edu.zzu.oj.enums.HttpStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

//业务异常类，用于处理业务上可以考虑到的类
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseException extends RuntimeException{
    private HttpStatus code;

    public BaseException(HttpStatus code) {
        this.code = code;
    }

    public BaseException(Throwable cause, HttpStatus code) {
        super(cause);
        this.code = code;
    }
}
