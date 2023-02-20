package lv.lu.df.combopt.no3inarow.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class Row {
    private Integer idx;

    @Override
    public String toString() {
        return "{" + idx +
                '}';
    }
}
