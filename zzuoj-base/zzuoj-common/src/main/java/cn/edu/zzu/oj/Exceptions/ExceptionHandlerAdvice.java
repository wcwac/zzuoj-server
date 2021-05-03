package cn.edu.zzu.oj.Exceptions;

//Runtime Exception,用来处理运行时异常


import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.entity.ResponseResult;
import cn.edu.zzu.oj.enums.HttpStatus;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(annotations = BaseResponse.class)
@ResponseBody
public class ExceptionHandlerAdvice {
    private static Logger log = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
    /**
     * 处理未捕获的Exception
     * @param e 异常
     * @return 统一响应体
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult handleException(Exception e){
        log.error(e.getMessage(),e);
        return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),null);
    }

    /**
     * 处理未捕获的RuntimeException
     * @param e 运行时异常
     * @return 统一响应体
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult handleRuntimeException(RuntimeException e){
        log.error(e.getMessage(),e);
        return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),null);
    }

    /**
     * 处理业务异常BaseException
     * @param e 业务异常
     * @return 统一响应体
     */
    @ExceptionHandler(BaseException.class)
    public ResponseResult handleBaseException(BaseException e){
        log.error(e.getMessage(),e);
        HttpStatus code=e.getCode();
        return new ResponseResult(code.value(),code.getReasonPhrase(),null);
    }
}
