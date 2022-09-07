package com.g5619.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Zheyin Zhang
 * @since 2022-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Message {
    private static final long serialVersionUID = 1L;

    private Long message_id;

    private Long group_id;

    private Long send_user_id;

    private Date createTime;
}
