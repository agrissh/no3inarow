package lv.lu.df.combopt.no3inarow;

import lv.lu.df.combopt.no3inarow.domain.No3inarowGrid;
import lv.lu.df.combopt.no3inarow.domain.No3inarowGridIO;
import org.optaplanner.benchmark.api.PlannerBenchmark;
import org.optaplanner.benchmark.api.PlannerBenchmarkFactory;

import java.io.File;

public class App {
    public static void main(String[] args) {

        PlannerBenchmarkFactory benchmarkFactory = PlannerBenchmarkFactory.createFromXmlResource(
                "lv/lu/df/combopt/no3inarow/benchmarkConfig.xml");
        PlannerBenchmark benchmark = benchmarkFactory.buildPlannerBenchmark();
        benchmark.benchmarkAndShowReportInBrowser();
        /*Integer N = 20;
        No3inarowGrid grid = No3inarowGrid.generateProblem(N, 2*N);
        No3inarowGridIO io = new No3inarowGridIO();
        io.write(grid, new File("data/n20.json"));*/
      // grid.getDotsOfRow(grid.getRows().get(0)).forEach(dot -> System.out.println(dot));
        /*grid.getDots().forEach(dot -> {
            grid.getDots().forEach(dot2 -> {
                grid.getDots().forEach(dot3 -> {
                    if (!(dot.equals(dot2) || dot.equals(dot3) || dot2.equals(dot3))) {
                        System.out.println("(" + dot.getRow().getIdx() + "," + dot.getCol().getIdx() + ")" +
                                " (" + dot2.getRow().getIdx() + "," + dot2.getCol().getIdx() + ")" +
                                " (" + dot3.getRow().getIdx() + "," + dot3.getCol().getIdx() + ") : " +
                                dot.isOnTheSameLine(dot2,dot3));
                    }
                 });
            }*/

    }
}
