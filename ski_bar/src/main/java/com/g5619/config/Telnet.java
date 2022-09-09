package com.g5619.config;


import lombok.Data;

/**
 * 统一返回格式
 */
@Data
public class Telnet {
    /**
     *
     */
    public static enum CODE{
        /**
         * 查询结果成功并且有数据
         */
        OK(200),

        /**
         * 查询结果成功并且无数据
         */
        NODATA(101),

        /**
         * 数据需要回执二次确认
         */
        RECEIPT(201),

        /**
         * 数据库异常错误
         */
        SQLERROR(401),
        /**
         * 参数不正确
         */
        PARAMETERERROR(501),
        /**
         * 令牌错误
         */
        TOKEN(502),
        /**
         * 需要人工介入的问题
         */
        ARTIFICIAL(601),

        /**
         * redis问题
         */
        REDISERROR(602);

        private Integer code;

        private CODE(Integer code){
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
    }
    private Integer code;
    private String msg;
    private Integer page;
    private Object data;
    private Long total;
    private Long datetime;

    public Telnet setCode(CODE code) {
        this.code = code.getCode();
        return this;
    }

    public Telnet setTotal(Long total) {
        this.total = total;
        return this;
    }

    public Telnet setMsg(String msg) {
        this.msg = msg;
        return this;
    }


    public Telnet setData(Object data) {
        this.data = data;
        return this;
    }


    public Telnet setPage(Integer page) {
        this.page = page;
        return this;
    }
    public Telnet setPage(Integer page, Long total) {
        this.page = page;
        this.total=total;
        return this;
    }
    public Long getDatetime() {
        return datetime;
    }

    public Telnet setDatetime(Long datetime) {
        this.datetime = datetime;
        return this;
    }


    @Override
    public String toString() {
        return "Telnet{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
