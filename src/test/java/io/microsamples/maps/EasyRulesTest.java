package io.microsamples.maps;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;

class EasyRulesTest {
    private RulesEngine rulesEngine = new DefaultRulesEngine();


    @BeforeEach
    void setUp(){

    }

    @Test
    void shouldRunRules(){
        AtomicBoolean eligible = new AtomicBoolean(false);

        Rule memberRule = new RuleBuilder()
                .name("some member rule")
                .description("Validate member.")
                .when(facts -> ((Member)facts.get("member")).isValid())
                .then(facts -> eligible.set(true))
                .build();

        Rule planRule = new RuleBuilder()
                .name("some plan rule")
                .description("Validate plan.")
                .when(facts -> ((Plan)facts.get("plan")).isGood())
                .then(facts -> eligible.set(true))
                .build();

        Rules rules = new Rules();
        rules.register(memberRule);
        rules.register(planRule);

        Facts  facts = new Facts();
        facts.put("member", Member.builder().name("Alex").valid(true).build());
        facts.put("plan", Plan.builder().name("Good Plan").good(true).build());

        rulesEngine.fire(rules, facts);

        assertThat(eligible).isTrue();

    }
}
