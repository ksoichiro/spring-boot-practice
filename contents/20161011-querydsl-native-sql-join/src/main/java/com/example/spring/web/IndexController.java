package com.example.spring.web;

import com.example.spring.service.AService;
import com.mysema.query.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {
    @Autowired
    private AService aService;

    /**
     * You can see a table like this:
     * <table>
     * <tr><td>ID</td>	<td>A</td>	<td>B</td>	<td>C</td>	<td>D</td>	<td>E1</td>	<td>E2</td>	<td>E3</td></tr>
     * <tr><td>1</td>	<td>a1</td>	<td>b1</td>	<td>c1</td>	<td>d1</td>	<td>e1</td>	<td>e2</td>	<td>e3</td></tr>
     * <tr><td>2</td>	<td>a2</td>	<td>b2</td>	<td>c2</td>	<td>d2</td>	<td>e4</td>	<td>e5</td> <td>&nbsp;</td></tr>
     * <tr><td>3</td>	<td>a3</td>	<td>b3</td>	<td>c3</td>	<td>d3</td>	<td>e6</td>	<td>&nbsp;</td>	<td>e7</td></tr>
     * </table>
     */
    @RequestMapping("/")
    public String index(Model model, @PageableDefault(size = 3) Pageable pageable) {
        model.addAttribute("aList", aService.findAll(pageable));
        model.addAttribute("aTupleList", aService.findAllAsTuple(pageable));
        return "index";
    }

    public Integer getInt(Tuple tuple, int index) {
        return tuple.get(index, Integer.class);
    }

    public String getString(Tuple tuple, int index) {
        return tuple.get(index, String.class);
    }
}
