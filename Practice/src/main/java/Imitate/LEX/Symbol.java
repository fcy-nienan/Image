package Imitate.LEX;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-09-10  2:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Symbol {
    private String str;
    private int coding;
    private Tokens tokens;
    public Symbol(String str,int co){
        this.str=str;
        this.coding=co;
    }
}
