package com.yjx.mongo.document.base;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <p>
 *
 * </p>
 *
 * @author yejx
 * @date 2019/9/17 17:27
 */
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
//@Document("test")
public class Test {

    /**
     * 用户名称
     */
    @Id
    private String Id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户类型编码
     */
    private Integer userTypeCode;

    /**
     * 用户类型名称
     */
    private String userTypeName;

    /**
     * 创建时间（时间戳，毫秒）
     */
    private Long createTime;

    /**
     * 推送时间（时间字符串格式：yyyy-MM-dd HH:mm:ss）
     */
    private String createTimeStr;

    /**
     * 是否删除（0:否,1:是）
     */
    @Builder.Default
    private int isDelete = 0;
}
