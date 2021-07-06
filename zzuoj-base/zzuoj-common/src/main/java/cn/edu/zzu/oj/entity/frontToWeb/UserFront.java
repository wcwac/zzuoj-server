package cn.edu.zzu.oj.entity.frontToWeb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFront extends BaseFront{

    @Pattern(regexp = "^[0-9]{10,16}$", message = "请输入正确的学号")
    @NotBlank(message = "学号不能为空")
    private String userId;

    @Email(message = "请输入正确的邮箱")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Length(min = 4, max = 32, message = "密码长度必须在4-32位之间")
    @NotBlank(message = "密码不能为空")
    private String password;

    @Length(min = 2, max = 30, message = "昵称必须在2-30位之间")
    @Pattern(regexp = "^[0-9a-zA-Z]*$")
    private String nickname;

    @Length(min = 2, max = 20, message = "学校名必须在2-30位之间")
    private String school;
}
