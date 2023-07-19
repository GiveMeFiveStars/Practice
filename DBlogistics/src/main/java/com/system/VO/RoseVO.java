package com.system.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class RoseVO {
    private String name;
    @JsonProperty("value")
    private Integer count;
}
