package com.bnz.jpa.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest //Jpa만 Slicing Test(h2 in-memory db 필요)
//@SpringBootTest //전체 Bean을 등록해서 Test
public class AccountRepositoryTest {

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate JdbcTemplate;

    @Autowired
    AccountRepository accountRepository;

    /**
     * slicing test를 위해 h2 inmemory db dependency 추가
     */
    @Test
    public void di() throws Exception {
        Account account = new Account();
        account.setUsername("bonzo");
        account.setPassword("pass");

        Account newAccount = accountRepository.save(account);

        assertThat(newAccount).isNotNull();
//
        Optional<Account> existingAccount = accountRepository.findByUsername(newAccount.getUsername());
        assertThat(existingAccount).isNotNull();



    }




}
