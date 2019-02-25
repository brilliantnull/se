package esmaster.enums;

/**
 * @Auther: bai
 * @Date: 2018/12/25 10:22
 * @Description:    暂时没用到
 */

public enum ResultEnum {

    RESULT_NOT_EXIT(1, "结果不存在"),

    ;

    private Integer code;

    private String message;

    ResultEnum() {
    }

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
