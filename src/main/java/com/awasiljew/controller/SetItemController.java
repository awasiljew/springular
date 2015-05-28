package com.awasiljew.controller;

import com.awasiljew.model.SelectableItem;
import com.awasiljew.model.SetItem;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static java.util.Arrays.asList;

@Controller
public class SetItemController {

    private static List<SetItem> items = asList(
            new SetItem("One"),
            new SetItem("Two"),
            new SetItem("Three"),
            new SetItem("Four")
    );

    private static SetItem selected = items.get(0);

    @ResponseBody
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public List<SelectableItem> items() {
        return FluentIterable.from(items)
                .transform(new Function<SetItem, SelectableItem>() {
                    @Override
                    public SelectableItem apply(SetItem setItem) {
                        return new SelectableItem(
                                setItem.getName(),
                                selected.getName().equals(setItem.getName())
                        );
                    }
                })
                .toList();
    }

    @ResponseBody
    @RequestMapping(value = "/change_item", method = RequestMethod.GET)
    public List<SelectableItem> changeItem(@RequestParam(value = "name", defaultValue = "One") final String name) {
        selected = Iterables.tryFind(items, new Predicate<SetItem>() {
            @Override
            public boolean apply(SetItem setItem) {
                return name.equals(setItem.getName());
            }
        }).or(items.get(0));
        return items();
    }

}
