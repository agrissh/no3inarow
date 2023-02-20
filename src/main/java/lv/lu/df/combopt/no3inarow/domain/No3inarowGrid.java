package lv.lu.df.combopt.no3inarow.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@PlanningSolution @NoArgsConstructor
public class No3inarowGrid {
    private static Integer CURRENT_ID = 1;

    @Setter @Getter @PlanningId
    private Integer gridId;

    @Setter @Getter
    private Integer N;

    @Getter @Setter @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "cols")
    private List<Column> columns = new ArrayList<>();

    @ValueRangeProvider(id = "rows")
    @Setter @Getter @ProblemFactCollectionProperty
    private List<Row> rows = new ArrayList<>();

    @Setter @Getter @PlanningEntityCollectionProperty
    private List<Dot> dots = new ArrayList<>();

    @Setter @Getter @PlanningScore
    SimpleScore score;

    @JsonIgnore
        public List<Dot> getDotsForRowAndColumn(Row row, Column col) {
            return this.getDots().stream()
                    .filter(dot -> dot.getRow().equals(row) && dot.getCol().equals(col))
                    .collect(Collectors.toList());
    }

    @JsonIgnore
    public static No3inarowGrid generateProblem(Integer numberOfRows, Integer numberOfDots) {
        No3inarowGrid grid = new No3inarowGrid();
        grid.gridId = CURRENT_ID;
        CURRENT_ID++;
        grid.N = numberOfRows;
        for (int i=0;i<numberOfRows;i++) {
            Row row = new Row();
            row.setIdx(i);
            grid.getRows().add(row);

            Column col = new Column();
            col.setIdx(i);
            grid.getColumns().add(col);
        }

        for (int i=0;i<numberOfDots;i++) {
            Dot dot = new Dot();
            dot.setId(CURRENT_ID);
            CURRENT_ID++;
            grid.getDots().add(dot);
        }

        return grid;
    }

    @JsonIgnore
    public static No3inarowGrid generateProblemFullWithDots(Integer numberOfRows) {
        No3inarowGrid grid = new No3inarowGrid();
        grid.gridId = CURRENT_ID;
        CURRENT_ID++;
        grid.N = numberOfRows;
        for (int i = 0; i < numberOfRows; i++) {
            Row row = new Row();
            row.setIdx(i);
            grid.getRows().add(row);

            Column col = new Column();
            col.setIdx(i);
            grid.getColumns().add(col);
        }

        grid.getRows().forEach(row -> {
            grid.getColumns().forEach(col -> {
                Dot dot = new Dot();
                dot.setCol(col);
                dot.setRow(row);
                grid.getDots().add(dot);
            });
        });

        return grid;
    }


}
