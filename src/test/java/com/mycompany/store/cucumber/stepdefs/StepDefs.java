package com.mycompany.store.cucumber.stepdefs;

import com.mycompany.store.StoreApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = StoreApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
