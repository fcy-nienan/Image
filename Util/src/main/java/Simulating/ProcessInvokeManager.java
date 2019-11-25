package Simulating;

import Simulating.MiddleMode.ProcessInvoke;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class ProcessInvokeManager implements Runnable{
    private static Logger logger = Logger.getLogger(ProcessInvokeManager.class.getName());
    private Set<ProcessInvoke> map=new HashSet<>();

    public boolean register(ProcessInvoke invoke){
        return map.add(invoke);
    }
    public boolean remove(ProcessInvoke invoke){
        return map.remove(invoke);
    }

    @Override
    public void run() {

    }
}
