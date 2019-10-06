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
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
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
		FlowRule rule = new FlowRule();
		rule.setResource("createOrder");
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		rule.setCount(10);
		List<FlowRule> rules = new ArrayList<FlowRule>();
		rules.add(rule);
		FlowRuleManager.loadRules(rules);
		
		DegradeRule rule2 = new DegradeRule();
		rule2.setResource("createOrder");
		rule2.setGrade(RuleConstant.DEGRADE_GRADE_RT);
		rule2.setCount(10);
		rule2.setTimeWindow(10);
		List<DegradeRule> rules2 = new ArrayList<DegradeRule>();
		rules2.add(rule2);
		DegradeRuleManager.loadRules(rules2);
		
	}

}
