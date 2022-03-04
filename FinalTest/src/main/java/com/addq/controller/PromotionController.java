package com.addq.controller;

import com.addq.model.Promotion;
import com.addq.service.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class PromotionController {
    @Autowired
    private IPromotionService promotionService;
    @GetMapping("")
    public ModelAndView index(@RequestParam("search") Optional<String> search, Pageable pageable) {
        Page<Promotion> promotions;
        if(search.isPresent()){
            promotions = promotionService.findAllByTitleContaining(search,pageable);
        } else {
            promotions = promotionService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("promotions",promotions);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("create");
        modelAndView.addObject("promotion", new Promotion());
        return  modelAndView;
    }
    @PostMapping("save")
    public ModelAndView save(@Valid @ModelAttribute("promotion") Promotion promotion, BindingResult bindingResult) {
        new Promotion().validate(promotion, bindingResult);
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasFieldErrors()){
            modelAndView.setViewName("create");
            modelAndView.addObject("promotion", promotion);
            modelAndView.addObject("message","Add Fail!");
            return modelAndView;
        }
        else {
            promotionService.save(promotion);
            modelAndView.setViewName("create");
            modelAndView.addObject("promotion", promotion);
            modelAndView.addObject("message","Add Success!");
            return modelAndView;
        }

    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("promotion",promotionService.findById(id).get());
        modelAndView.setViewName("edit");
        return  modelAndView;
    }
    @PostMapping("edit")
    public ModelAndView saveEdit(@Valid @ModelAttribute("promotion") Promotion promotion,BindingResult bindingResult) {
        new Promotion().validate(promotion, bindingResult);
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasFieldErrors()){
            modelAndView.setViewName("edit");
            modelAndView.addObject("promotion", promotion);
            modelAndView.addObject("message","Edit Fail!");
            return modelAndView;
        }
        else {
            promotionService.save(promotion);
            modelAndView.setViewName("edit");
            modelAndView.addObject("promotion", promotion);
            modelAndView.addObject("message","Edit Success!");
            return modelAndView;
        }
    }
    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("promotion",promotionService.findById(id));
        modelAndView.setViewName("delete");
        return  modelAndView;
    }

    @PostMapping("delete")
    public ModelAndView delete(@ModelAttribute("promotion") Promotion promotion, Pageable pageable) {
        promotionService.delete(promotion.getId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message","Delete Success!");
        modelAndView.addObject("promotions",promotionService.findAll(pageable));
        modelAndView.setViewName("index");

        return modelAndView;
    }
}
