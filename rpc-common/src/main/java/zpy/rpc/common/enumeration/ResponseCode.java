package zpy.rpc.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseCode {
    SUCCESS(200,"调用方法成功"),
    FAIL(500,"调用方法失败"),
    METHOD_NOT_FOUND(501,"未找到指定方法"),
    CLASS_NOT_FOUND(502,"未找到指定类");

    int code;
    String message;
}
