package ru.eight.App.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.eight.App.PayService;
import ru.eight.App.Payment;

import java.io.IOException;
import java.util.List;

@Controller
public class PayController {
    @Autowired
    PayService payService;

    @GetMapping("/pays")
    @ResponseBody
    public List<Payment> getAllPayments() {
        return payService.getAllPayments();
    }

    @PostMapping(path = "/create_pay", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity createPay(@RequestBody String req) throws IOException {
        payService.createPay(req);
        return ResponseEntity.ok("payment was added");
    }

    @GetMapping("/get_pay")
    @ResponseBody
    public Payment getPayById(@RequestParam String id) {
        return payService.getPay(id);
    }
}
