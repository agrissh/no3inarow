package lv.lu.df.combopt.no3inarow.domain;


import org.optaplanner.persistence.jackson.impl.domain.solution.JacksonSolutionFileIO;

public class No3inarowGridIO extends JacksonSolutionFileIO<No3inarowGrid> {
    public No3inarowGridIO() {
        super(No3inarowGrid.class);
    }
}
