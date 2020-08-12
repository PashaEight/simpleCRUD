package ru.eight.App.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.eight.App.PayService;
import ru.eight.App.Payment;

import java.io.IOException;

@Controller
public class FirstController {
    @Autowired
    PayService ps;

    @GetMapping("/home")
    public String sayHello() {
        return "first/hello";
    }

    @GetMapping("/pays")
    @ResponseBody
    public String getAllPayments() {
        StringBuffer sb = new StringBuffer();
        for (Payment allPayment : ps.getAllPayments()) {
            sb.append(allPayment.toString() + "\n");
        }

        return sb.toString();
    }

    @PostMapping(path = "/create_pay", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity createPay(@RequestBody String req) throws IOException {
        ps.createPay(req);
        return ResponseEntity.ok("payment was added");
    }

    @GetMapping("/get_pay")
    @ResponseBody
    public String getPayById(@RequestParam String id) {
        StringBuffer sb = new StringBuffer();
        sb.append(ps.getPay(id));
        return sb.toString();

    }
}
