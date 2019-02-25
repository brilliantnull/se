package esmaster.exception;

import esmaster.enums.ResultEnum;

/**
 * @Auther: bai
 * @Date: 2018/12/25 10:18
 * @Description:    暂时没用到
 */
public class SearchException extends RuntimeException{

    private Integer code;

    public SearchException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SearchException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
