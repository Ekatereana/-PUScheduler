public class Main {
    public static void main(String[] args) {
        Scheduler scheduler = null;
        try {
            scheduler = new Scheduler();
            Thread thread = new Thread(scheduler);
            thread.start();
            scheduler.execute(new CPUProcess(CPUProcess.ProcessType.INTERACTIVE, 0, 220, "P1"));
            scheduler.execute(new CPUProcess(CPUProcess.ProcessType.BATCH, 1, 400, "P3"));
            Thread.sleep(50);
            scheduler.execute(new CPUProcess(CPUProcess.ProcessType.INTERACTIVE, 2, 20, "P2"));
            scheduler.execute(new CPUProcess(CPUProcess.ProcessType.BATCH, 3, 200, "P4"));
            Thread.sleep(250);
            scheduler.execute(new CPUProcess(CPUProcess.ProcessType.INTERACTIVE, 4, 20, "P5"));
            scheduler.close();
//            generateSeries(scheduler, 150, true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //    part to generate series for creating graphics
    public static void generateSeries(Scheduler scheduler, int interval, boolean isRandom) throws InterruptedException {
        int name = 0;
        int minPriority = 5;
        int arriveTime = 0;
        String process = "";
        while (name < 100) {
            process = "P" + name;
            name++;
            scheduler.execute(new CPUProcess(CPUProcess.ProcessType.getByPriority((int) (Math.random() * (minPriority + 1))),
                    arriveTime, (int) (Math.random() * 151), process));
            if (isRandom) {
                Thread.sleep(new Long((int)(Math.random()*interval)));
            } else {
                Thread.sleep(new Long(interval));
            }
        }
        scheduler.close();


    }

}
