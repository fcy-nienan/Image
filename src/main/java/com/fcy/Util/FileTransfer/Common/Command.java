package com.fcy.Util.FileTransfer.Common;

import java.io.Serializable;

public interface Command extends Serializable {
    void execute();
}
