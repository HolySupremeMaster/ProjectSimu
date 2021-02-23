
import java.util.ArrayList;

public class State {

    private String name="";
    private double timeframe = 0.0;
    private double iterationcompletionval = 1.0;
    private ArrayList<State> input = new ArrayList<State>();
    private ArrayList<State> output = new ArrayList<State>();

    public State() {
    }

    public State(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(double timeframe) {
        this.timeframe = timeframe;
    }

    public State getInput(int index) {
        return this.input.get(index);
    }

    public void addInput(State input) {
        this.input.add(input);
    }

    public State getOutput(int index) {
        return this.output.get(index);
    }

    public boolean isOutputEmpty() {
        return this.output.isEmpty();
    }


    public void addOutput(State output) {
        this.output.add(output);
    }

    public double getIterationcompletionval() {
        return this.iterationcompletionval;
    }

    public void setIterationcompletionval(double iterationcompletionval) {
        this.iterationcompletionval = iterationcompletionval;
    }

    @Override
    public String toString() {
        return name  +
                ", timeframe=" + String.format("%.2f", timeframe)
                ;
    }
}
