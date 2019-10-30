package DesignPattern.Visit;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  11:40
 */
public class Computer implements ComputerPart {
    @Getter
    private String name="Computer";
    private List<ComputerPart> part;
    public Computer(){
        part=new ArrayList<>();
    }
    @Override
    public void accept(ComputerVisit visit) {
        part.forEach(e->{
            e.accept(visit);
        });
        visit.visit(this);
    }
    public void add(ComputerPart part){
        this.part.add(part);
    }
}
