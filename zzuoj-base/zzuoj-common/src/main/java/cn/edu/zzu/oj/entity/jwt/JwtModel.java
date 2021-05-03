package cn.edu.zzu.oj.entity.jwt;

import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtModel {

    private String userName;

    private List<String> roleIdList;

}
