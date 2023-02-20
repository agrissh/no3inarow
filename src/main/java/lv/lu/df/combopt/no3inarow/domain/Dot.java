package lv.lu.df.combopt.no3inarow.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity @NoArgsConstructor
public class Dot {
    @Getter @Setter @PlanningId
    private Integer id;

    @Getter @Setter @PlanningVariable(valueRangeProviderRefs = "cols")
    private Column col;

    @Getter @Setter @PlanningVariable(valueRangeProviderRefs = "rows")
    private Row row;

    @Override
    public String toString() {
        return "(" + r() +
                ", " + c() + ')';
    }

    public Integer c() {
        return col != null ? col.getIdx() : -1;
    }

    public Integer r() {
        return row != null ? row.getIdx() : -1;
    }

    public Integer getDiagonalIdx(Diagonal diagonal) {
        // if steep, then coefficient * row - col
        // otherwise coefficient * col - row
        return diagonal.getSteep() ? diagonal.getCoefficient() * row.getIdx() - col.getIdx() : diagonal.getCoefficient() * col.getIdx() - row.getIdx();
    }

    public Boolean isOnTheSameLine(Dot dot2, Dot dot3) {
        return (this.r() == dot2.r() && this.r() == dot3.r()) ||
                (this.r() != dot2.r() && this.r() != dot3.r() && ((this.c() - dot2.c()) * 1.0 / (this.r() - dot2.r()) == (this.c() - dot3.c()) * 1.0 / (this.r() - dot3.r())));
    }


}
