package com.awasiljew.controller;

import com.awasiljew.model.SetItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static java.util.Arrays.asList;

@Controller
public class SetItemController {

    @ResponseBody
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public List<SetItem> items() {
        return asList(
                new SetItem("First", false),
                new SetItem("Second", true),
                new SetItem("Third", false)
        );
    }

}
