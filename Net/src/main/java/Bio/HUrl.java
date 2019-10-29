package com.fcy.Net.Bio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-11  0:12
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HUrl {
    private String line;
    private String protocol;
    private String url;
    private String version;
    public HUrl(String line){
        this.line=line;
    }
}
