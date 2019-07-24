package com.binance.assignments.controllers;

import com.binance.assignments.models.Company;
import com.binance.assignments.service.ProcessInvoice;
import com.binance.assignments.models.Timetable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class GuestController {
    @Autowired
    ProcessInvoice processInvoice;
    private static final Logger logger = LogManager.getLogger(GuestController.class);

    @GetMapping("/guest")
    public String guest() {
        return "guest";
    }


    @RequestMapping(value = "/upload-file", method = RequestMethod.POST, consumes = "multipart/form-data")
    public String addFile(Model model, @RequestParam("file") MultipartFile file) {
        try {
            List<Timetable> read = processInvoice.readTimeTables(file);
            List<Company> companyInvoices = processInvoice.getCompanyInvoices(read);
            logger.info("After reading companyInvoices "+companyInvoices.size());
            model.addAttribute("data", companyInvoices);
            model.addAttribute("success",true);
        } catch (Exception ex) {
            model.addAttribute("errordata", ex.getMessage());
            model.addAttribute("success",false);
            logger.info(ex);
        }
        return "result";
    }
}
