import java.util.HashMap;
import java.util.LinkedList;

public class Scheduler implements Runnable {
    private final int QUANTUM = 70;
    private int highestP = 0;
    private boolean isRunning = true;
    private HashMap<Integer, LinkedList<CPUProcess>> readyQueue = new HashMap<>();

    public void close() {
        this.isRunning = false;
    }

    public Scheduler() {
        readyQueue.put(CPUProcess.ProcessType.INTERACTIVE.getPriority(), new LinkedList<>());
        readyQueue.put(CPUProcess.ProcessType.BATCH.getPriority(), new LinkedList<>());

    }

    public void execute(CPUProcess process) {
        System.out.println("new process added: \n" + process.toString() + " to " + process.getProcessType());
        readyQueue.get(process.getProcessType().getPriority()).add(process);
        this.highestP = process.getProcessType().getPriority();
    }

    public void run() {
        boolean isAlive = true;
        while (isAlive) {
            isAlive = isRunning || !readyQueue.get(0).isEmpty() && !readyQueue.get(1).isEmpty();
            try {
                if (highestP < 1) {
                    executeByRRobin(readyQueue.get(highestP));
                } else {
                    executedByFIFO(readyQueue.get(highestP));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void executedByFIFO(LinkedList<CPUProcess> queue)
            throws InterruptedException {
        int priority;
        boolean isChecked;
        CPUProcess current;
        while (!queue.isEmpty()) {
            priority = queue.peek().getProcessType().getPriority();
            isChecked = highestP >= priority;
            current = queue.poll();
            current.print(0);
            while (current.getCompleteTime() - QUANTUM > 0 && isChecked) {
                current.setCompleteTime(current.getCompleteTime() - QUANTUM);
                Thread.sleep(QUANTUM);
                isChecked = highestP >= priority;
            }
            if (current.getCompleteTime() - QUANTUM <= 0 && isChecked) {
                Thread.sleep(current.getCompleteTime());
                current.print(3);
            }
            if (!isChecked) {
                current.print(1);
                queue.addFirst(current);
                break;
            }
        }

    }


    private void executeByRRobin(LinkedList<CPUProcess> queue) throws InterruptedException {
        CPUProcess current;
        boolean isChecked;
        while (!queue.isEmpty()) {
            isChecked = true;
            current = queue.poll();
            current.print(0);
            while (isChecked) {
                isChecked = checkRobinQueue(current);
            }
        }
    }

    private boolean checkRobinQueue(CPUProcess process) throws InterruptedException {
        LinkedList<CPUProcess> queue = readyQueue.get(process.getProcessType().getPriority());
        if (process.getCompleteTime() - QUANTUM <= 0) {
            Thread.sleep(process.getCompleteTime());
            process.print(3);
            if (queue.isEmpty()) {
                highestP = highestP == 0 ? 1 : highestP;
            }
            return false;
        } else {
            if (queue.isEmpty()) {
                process.setCompleteTime(process.getCompleteTime() - QUANTUM);
                Thread.sleep(QUANTUM);
            } else {
                process.print(1);
                queue.addLast(process);
                return false;
            }
        }
        return true;

    }
}
