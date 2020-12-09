import java.util.HashMap;

public class CPUProcess implements Comparable<CPUProcess> {
    private ProcessType processType;
    private Double arriveTime;
    private int completeTime;
    private String processId;

    public CPUProcess(ProcessType processType, Double arriveTime, int completeTime, String processId) {
        this.processType = processType;
        this.arriveTime = arriveTime;
        this.completeTime = completeTime;
        this.processId = processId;
    }


    public int getCompleteTime() {
        return completeTime;
    }

    public ProcessType getProcessType() {
        return processType;
    }

    public void setCompleteTime(int completeTime) {
        this.completeTime = completeTime;
    }

    @Override
    public int compareTo(CPUProcess o) {
        return o.getProcessType().getPriority() < processType.getPriority() ? 1 : 0;
    }

    @Override
    public String toString() {
        return "CPUProcess added ( " + processType +
                "," + arriveTime +
                ", '" + processId  +
                ')';
    }

    public void print(int id){
        System.out.println("=======================================");
        System.out.println("Process: (" + processId + "," + arriveTime + ", " + processType + ")");
        switch (id){
            case 0:
                System.out.println("started...");
                break;
            case 1:
                System.out.println("stopped...");
                break;
            case 2:
                System.out.println("re-started...");
                break;
            case 3:
                System.out.println("completed.");
                break;
        }
        System.out.println("=======================================");
    }



    enum ProcessType {
        INTERACTIVE(0),
        BATCH(1);

        private int priority;

        private static HashMap<Integer, ProcessType> map;

        static {
            map = new HashMap<Integer, ProcessType>();
            for (ProcessType v : ProcessType.values()) {
                map.put(v.getPriority(), v);
            }
        }

        ProcessType(int priority) {
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }

        public static ProcessType getByPriority(int priority){
            return map.get(priority);
        }
    }
}
