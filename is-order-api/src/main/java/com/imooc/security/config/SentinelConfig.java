/**
 * 
 */
package com.imooc.security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

/**
 * @author jojo
 *
 */
@Component
public class SentinelConfig implements ApplicationListener<ContextRefreshedEvent> {
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		List<FlowRule> rules = new ArrayList<>();
	    FlowRule rule = new FlowRule();
	    rule.setResource("createOrder");
	    rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
	    rule.setCount(1);
	    rules.add(rule);
	    FlowRuleManager.loadRules(rules);
	}

}
