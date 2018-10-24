package com.fcy.Net.Bio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-10  23:48
 */
@Data
@ToString
@NoArgsConstructor
public class Http {
    private HUrl url;
    private HttpHeader head;
    private String body;
    public Http(String url,List<String> head,String body){
        this.url=new HUrl(url);
        this.head=new HttpHeader(head);
        this.body=body;
    }
}
