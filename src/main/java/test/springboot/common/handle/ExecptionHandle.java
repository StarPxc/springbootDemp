package test.springboot.common.handle;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import test.springboot.common.enums.ResultEnum;
import test.springboot.common.execption.CustomException;
import test.springboot.common.resp.ApiResult;
import test.springboot.common.utils.RestUtil;

/**
 * @author Ethanp
 * @version V1.0
 * @Package com.db.fiftysql.common.execption
 * @Description: TODO 全局的异常处理
 * @date 2018/1/29 10:10
 */
@ControllerAdvice
@Slf4j
public class ExecptionHandle {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResult handle(Exception e) {
        if (e instanceof CustomException) {//如果是自定义异常
            CustomException CustomException = (CustomException) e;
            log.error(CustomException.getMessage());
            return RestUtil.error(CustomException.getCode(), CustomException.getMessage());
        } else if (e instanceof MultipartException) {
            return RestUtil.error(ResultEnum.FILE_TOO_BIG.getCode(), ResultEnum.FILE_TOO_BIG.getMsg());
        } else {
            log.error("【系统异常】", e);
            return RestUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }

    }
}