package cn.edu.zzu.oj.entity.frontToWeb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ContestFront  extends BaseFront{
    private String title;

    private List<Date> time;

    private String defunct;

    private String description;

    private Integer isPrivate;

    /**
     * bits for LANG to mask
     */
    private List<String> langmask;

    private String password;

    private String groupId;

    private String problems;
}
