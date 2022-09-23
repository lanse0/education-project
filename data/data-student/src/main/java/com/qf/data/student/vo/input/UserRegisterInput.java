package com.qf.data.student.vo.input;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qf.data.student.valid.MyValid;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 注册的入参对象
 * 请求发送什么参数 这个类就有什么属性
 * author 14526
 * create_time 2022/9/24
 */
@Data
@ToString(callSuper = true) //打印父类的属性
@MyValid(message = "年龄和生日不匹配") //自定义校验
public class UserRegisterInput {
    //用户名
    @NotBlank(message = "用户名不能为空！")
    private String username;
    //密码
    @NotBlank(message = "密码不能为空！")
    private String password;
    //姓名
    @NotBlank(message = "姓名不能为空！")
    @Size(min = 2, max = 5,message = "名字长度不合法！")
    private String name;
    //性别 0男 1女
    @Min(value = 0,message = "性别参数不合法!")
    @Max(value = 1,message = "性别参数不合法!")
    private Integer sex;
    //头像
    private String header;
    //角色 0管理员 1教师 2学生
    private Integer role;

    /**
     * 使用自定义校验
     */
    //@MyValid(message = "年龄不是偶数")
    private Integer age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
}
