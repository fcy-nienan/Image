package DesignPattern.Builder.link;

public class ComputerBuilder {
    private String cpu;
    private int memory;
    private int gpu;
    private int hardware;
    public ComputerBuilder cpu(String cpu){
        this.cpu=cpu;
        return this;
    }
    public ComputerBuilder memory(int memory){
        this.memory=memory;
        return this;
    }
    public ComputerBuilder gpu(int gpu){
        this.gpu=gpu;
        return this;
    }
    public ComputerBuilder hardware(int hardware){
        this.hardware=hardware;
        return this;
    }
    public Computer build(){
        return build(this);
    }
    private static Computer build(ComputerBuilder builder){
        Computer computer=new Computer();
        if (builder.cpu!=null)
            computer.setCpu(builder.cpu);
        if (builder.gpu!=0)
            computer.setGpu(builder.gpu);
        if (builder.hardware!=0)
            computer.setHardware(builder.hardware);
        if (builder.memory!=0)
            computer.setMemory(builder.memory);
        return computer;
    }
}
