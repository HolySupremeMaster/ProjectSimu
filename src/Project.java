

import java.util.ArrayList;

public class Project {

    private int iteration = 0;
    private ArrayList<State> projectStates = new ArrayList<State>();
    private State currentState;

    public void Init() {
        //State objects
        this.projectStates.add(new State("Communication"));  //0
        this.projectStates.add(new State("Planning"));       //1
        this.projectStates.add(new State("Modelling"));      //2
        this.projectStates.add(new State("Construction"));   //3
        this.projectStates.add(new State("Deployment"));     //4
        //linking them together
        this.projectStates.get(0).addOutput(this.projectStates.get(1));
        this.projectStates.get(1).addInput(this.projectStates.get(0));
        this.projectStates.get(1).addOutput(this.projectStates.get(2));
        this.projectStates.get(1).addOutput(this.projectStates.get(0));
        this.projectStates.get(2).addInput(this.projectStates.get(1));
        this.projectStates.get(2).addOutput(this.projectStates.get(3));
        this.projectStates.get(3).addInput(this.projectStates.get(2));
        this.projectStates.get(3).addOutput(this.projectStates.get(4));
        this.projectStates.get(3).addOutput(this.projectStates.get(0));
        //setting project iteration completion values
        this.projectStates.get(1).setIterationcompletionval(0.8);
        this.projectStates.get(2).setIterationcompletionval(0.8);
        this.projectStates.get(3).setIterationcompletionval(0.8);
        //set initial project state
        currentState = this.projectStates.get(0);
        currentState.setTimeframe(1.0);
        //let the project run while theres output state and we have timeframe left
        while (!currentState.isOutputEmpty() && currentState.getTimeframe() > 0) {
            printStates();
            //if state timeframe is 0 then move on to the next state
            if (currentState.getTimeframe() <= 0.0) {
                this.currentState = currentState.getOutput(0);
            } else if (currentState.getTimeframe() > 0.0) {
                //process iteration
                double curtimeval = this.currentState.getTimeframe();
                this.iteration++;
                currentState.setTimeframe(currentState.getTimeframe() - currentState.getIterationcompletionval());
                //pass it on to next state if it exist
                if (!currentState.isOutputEmpty()) {
                    //check how much to pass to next state
                    if (currentState.getTimeframe() <= 0) {
                        currentState.getOutput(0).setTimeframe(currentState.getOutput(0).getTimeframe() + curtimeval);
                        this.currentState.setTimeframe(0);
                        this.currentState = currentState.getOutput(0);

                    } else {
                        currentState.getOutput(0).setTimeframe(currentState.getOutput(0).getTimeframe() + currentState.getIterationcompletionval());
                        //lets see if we have other output and pass it on there else just keep it
                        try {
                            currentState.getOutput(1).setTimeframe(currentState.getTimeframe());
                            currentState.setTimeframe(0);
                            currentState=currentState.getOutput(1);
                        } catch (IndexOutOfBoundsException e) {
                            //do nothing if it fails
                        }
                    }
                }
                //if no output and below 0 set to 0
                else if (currentState.getTimeframe() < 0) {
                    currentState.setTimeframe(0);
                }
            }
        }
        this.iteration++;
        printStates();
        System.out.println("Completed in " +this.iteration+ " iterations");
    }

    private void printStates() {
        //print out the states status
        for (State s : this.projectStates) {
            System.out.print(s.toString() + " | ");
        }
        System.out.println();
    }
}