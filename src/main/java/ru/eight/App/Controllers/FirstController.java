package ru.eight.App.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.eight.App.PayService;
import ru.eight.App.Payment;

@Controller
public class FirstController {
    @Autowired
    PayService ps;

    @GetMapping("/home")
    public String sayHello() {
        return "first/hello";
    }

    @GetMapping("/test")
    @ResponseBody
    public String getPaymentAmount() {
        return String.valueOf(ps.getPaymentAmountById(14));
    }

    @GetMapping("/allPays")
    @ResponseBody
    public String getAllPayments() {
        StringBuffer sb = new StringBuffer();
        for (Payment allPayment : ps.getAllPayments()) {
            sb.append(allPayment.toString() + "\n");
        }

        return sb.toString();
    }
}
