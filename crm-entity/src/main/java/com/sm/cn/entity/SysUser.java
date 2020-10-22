package com.sm.cn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sm.cn.groupvalidator.AddGroup;
import com.sm.cn.groupvalidator.UpdateGroup;
import com.sm.cn.valicatorannotion.SexValues;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author tjp
 * @since 2020-10-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**需要分组校验
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)

    @Null(groups = {AddGroup.class},message = "添加ID必须为空")
    @NotNull(groups = {UpdateGroup.class},message = "修改ID不能为空")
    private Long userId;

    /**  @NotBlank 不能为null且不能为空字符串和空白字符串
     * 用户账号
     */
@Pattern(regexp = "[a-zA-Z0-9_]{3,16}",message="数字字母下划线组成，长度3-16",groups ={AddGroup.class,UpdateGroup.class} )
@NotBlank(message = "用户名不为空")
    private String userName;

    /**
     * 用户昵称
     */
    @NotBlank(groups ={AddGroup.class,UpdateGroup.class})
    private String nickName;

    /**
     * 用户邮箱
     */
    @Email(message = "输入的必须是邮箱类型",groups ={AddGroup.class,UpdateGroup.class})
    @NotBlank(message = "邮箱不能为空",groups ={AddGroup.class,UpdateGroup.class})
    private String email;

    /**
     * 手机号码
     */
    @Pattern(regexp = "0?(13|14|15|18)[0-9]{9}",message = "必须手机号",groups ={AddGroup.class,UpdateGroup.class})
    @NotBlank(message = "手机号不能为空",groups ={AddGroup.class,UpdateGroup.class})
    private String phone;

    /**
     * 用户性别（0男 1女 2未知）
     */
    @SexValues(values ={"0","1"},message = "性别只能0或1",groups ={AddGroup.class,UpdateGroup.class})
    private String sex;

    /**
     * 头像地址
     */
    @URL(message = "请传递有效地址！",groups ={AddGroup.class,UpdateGroup.class})
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 帐号状态（1正常 0停用）
     */
    private Boolean status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private Integer delFlag;

    /**
     * 备注
     */
    private String remark;


}
