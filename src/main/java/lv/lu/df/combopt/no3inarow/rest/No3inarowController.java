package lv.lu.df.combopt.no3inarow.rest;

import lv.lu.df.combopt.no3inarow.domain.No3inarowGrid;
import org.optaplanner.core.api.score.ScoreManager;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.SolverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/optimizer")
public class No3inarowController {

    @Autowired
    private SolverManager<No3inarowGrid, Integer> solverManager;

    private Map<Integer, No3inarowGrid> gridList = new HashMap<Integer, No3inarowGrid>();

    @Autowired
    private ScoreManager<No3inarowGrid, HardMediumSoftScore> scoreManager;

    @PostMapping("/solve")
    @ResponseBody
    public void solve(@RequestParam(name = "N") Integer N, @RequestParam(name="d") Integer numberOfDots) {
        solverManager.solveAndListen(0, id -> No3inarowGrid.generateProblem(N, numberOfDots), solution -> gridList.put(solution.getGridId(), solution));
    }

    @GetMapping("/get")
    @ResponseBody
    public No3inarowGrid getSolution(@RequestParam(name="id") Integer id) {
        return gridList.get(id);
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list",
                gridList.values().stream()
                        .collect(Collectors.toList())
        );
        return "list";
    }

    @GetMapping("/show")
    public String show(@RequestParam(name = "gridId") Integer gridId, Model model) {
        No3inarowGrid grid = this.gridList.getOrDefault(gridId, null);
        model.addAttribute("indictmentMap", this.scoreManager.explainScore(grid).getIndictmentMap());
        model.addAttribute("grid", grid);
        return "show";
    }




}
