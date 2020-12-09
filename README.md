# CPU scheduler with MQS algorithm implementation
## DESCRIPTION
### Brief description
A test of implementing the basic work of MQS (Multiply Queue Scheduler) algorithm.
According to MQS all CPU process can be divided into separate groups. Each them has unique priority in
general process queue (0 - has the highest priority).

There are two types of CPU process
> INTERACTIVE -> priority = 0
> BATCH -> priority = 1

### Algorithm description
## DEMONSTRATION

##### Code example
```
scheduler = new Scheduler();
Thread thread = new Thread(scheduler);
thread.start();
scheduler.execute(new CPUProcess(CPUProcess.ProcessType.INTERACTIVE, 0.0, 220, "P1"));
scheduler.execute(new CPUProcess(CPUProcess.ProcessType.BATCH, 1.0, 400, "P3"));
Thread.sleep(50);
scheduler.execute(new CPUProcess(CPUProcess.ProcessType.INTERACTIVE, 2.0, 20, "P2"));
scheduler.execute(new CPUProcess(CPUProcess.ProcessType.BATCH, 3.0, 200, "P4"));
Thread.sleep(250);
scheduler.execute(new CPUProcess(CPUProcess.ProcessType.INTERACTIVE, 4.0, 20, "P5"));
scheduler.close();
```

#### Executing process with the highest priority.

##### Output
![The RR algorithm workflow](resources/RR.png "RRobin queue workflow example")

#### Stop and restart BATH process while INTERACTIVE process arrived.

##### Output
![Stopping process with lower priority](resources/MQS.png "RRobin queue workflow example")

#### Graphics

> Graphic of AVG(waiting time) for CPU processes (max complete time 150millis). Frequency axis
> refers to interval between adding requests to CPU.

##### Output
![Stopping process with lower priority](resources/AWG.png "RRobin queue workflow example")