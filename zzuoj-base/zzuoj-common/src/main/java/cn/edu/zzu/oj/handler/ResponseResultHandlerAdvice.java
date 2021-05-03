package cn.edu.zzu.oj.handler;

import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.entity.ResponseResult;
import cn.edu.zzu.oj.enums.HttpStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(annotations = BaseResponse.class)
public class ResponseResultHandlerAdvice implements ResponseBodyAdvice {
    private static Logger log = LoggerFactory.getLogger(ResponseResultHandlerAdvice.class);

    //返回true表示执行当前advice
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
//        MediaType.APPLICATION_JSON.equals(mediaType) || MediaType.APPLICATION_JSON_UTF8.equals(aClass)
        if(o instanceof ResponseResult){ // 如果响应返回的对象为统一响应体，则直接返回body
            return o;
        }else{
            //对于String要特殊处理，不然就有error 实现ResponseBbodyAdvice接口后，Controller层返回String类型报错的解决办法：https://juszoe.github.io/views/spring-boot/ResponseBodyAdvice%E5%BC%82%E5%B8%B8.html#%E9%97%AE%E9%A2%98
            ResponseResult responseResult = new ResponseResult(HttpStatus.OK.value(),HttpStatus.OK.getReasonPhrase(),o);
            if(o instanceof String) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    return objectMapper.writeValueAsString(responseResult);
                } catch (JsonProcessingException e) {
                    log.error("convert string to json fail!");
                    throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
            return responseResult;
        }
    }
}
