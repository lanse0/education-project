package com.qf.data.notify.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 邮件对象
 * author 14526
 * create_time 2022/10/13
 */
@Data
@Accessors(chain = true)
public class Email implements Serializable {
    //标题
    private String subject;
    //接收方
    private List<String> tos = new ArrayList<>();
    //邮件内容
    private String content;
    //附件
    private File attrFile;

    public Email addTo(String to) {
        tos.add(to);
        return this;
    }
}
