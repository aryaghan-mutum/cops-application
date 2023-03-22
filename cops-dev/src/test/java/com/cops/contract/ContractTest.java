package com.cops.contract;

import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.junitsupport.target.Target;
import au.com.dius.pact.provider.junitsupport.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import au.com.dius.pact.provider.spring.target.SpringBootHttpTarget;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringRestPactRunner.class)
@Provider("customer_provider")
@PactFolder("C:/dev/my-projects/app/cops-application/pacts")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContractTest {

    @TestTarget
    public final Target target = new SpringBootHttpTarget();

    // The 'as-is' service is used for all provider states, so no additional setup is needed

    @State("Customer GET: the customer ID matches an existing customer")
    public void customerSuppliedByCustomerGETExists() {
        System.out.println("ContractTest::customerSuppliedByCustomerGETExists");
    }

    @State("Customer GET: test2")
    public void test2() {
        System.out.println("ContractTest::test2");
    }

    @State("Customer GET: test3")
    public void test3() {
        System.out.println("ContractTest::test3");
    }

    @State("Customer GET: test4")
    public void test4() {
        System.out.println("ContractTest::test4");
    }

}