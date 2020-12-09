public class Main {
    public static void main(String[] args) {
        Scheduler scheduler = null;
        try {
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
