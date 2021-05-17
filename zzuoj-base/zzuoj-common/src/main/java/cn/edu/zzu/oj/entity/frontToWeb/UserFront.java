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

    @Pattern(regexp = "^[0-9]{10,16}$", message = "用户名必须由数字构成，且长度为10~16")
    @NotBlank(message = "学号不能为空")
    private String userId;

    @Email(message = "邮箱不合法")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Length(min = 4, max = 32, message = "密码长度必须在4-32位之间")
    @NotBlank(message = "密码不能为空")
    private String password;

    @Length(min = 2, max = 30, message = "昵称长度不合法，必须在2-30位之间")
    private String nickname;

    @Length(min = 2, max = 20, message = "学校长度不合法,必须在2-30位之间")
    private String school;
}
