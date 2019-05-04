package auctionapp.api;

import auctionapp.dao.entity.Person;
import auctionapp.manager.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accountAPI")
public class AccountApi {

    private AccountManager accountManager;

    @Autowired
    public AccountApi(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @PostMapping("/account")
    public String saveUser(@RequestBody Person person) {
        return accountManager.saveAccount(person);
    }
}
