package Main;

public class Interpreter {
    private boolean running;

    public boolean getRunning() { return running; }
    public void setRunning(boolean running) { this.running = running; }

    public Interpreter(){ setRunning(true); }
}
