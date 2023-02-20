package lv.lu.df.combopt.no3inarow.score;

import lv.lu.df.combopt.no3inarow.domain.Dot;
import org.drools.tms.beliefsystem.defeasible.Join;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

import static org.optaplanner.core.api.score.stream.Joiners.equal;

public class No3inarowConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {

        return new Constraint[] {
                sameSpot(constraintFactory),
                onTheSameLine(constraintFactory)
        };
    }

    private Constraint sameSpot(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEachUniquePair(Dot.class, equal(Dot::getCol), equal(Dot::getRow))
                .penalize(SimpleScore.ONE, (d1, d2) -> 1000)
                .asConstraint("sameSpot");
    }

    private Constraint onTheSameLine(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Dot.class)
                .join(Dot.class, Joiners.lessThan(Dot::getId))
                .join(Dot.class)
                .filter((dot1,dot2,dot3) -> (dot3.getId()<dot1.getId()) && (dot3.getId()<dot1.getId())
                        && dot1.isOnTheSameLine(dot2, dot3))
                .penalize(SimpleScore.ONE)
                .asConstraint("onTheSameLine");
    }



}
