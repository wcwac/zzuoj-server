/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the General Public License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package cn.edu.zzu.oj.dto;

import cn.edu.zzu.oj.entity.BaseDTO;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO extends BaseDTO {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Long size;

    private String name;

    private String extensionName;

}
