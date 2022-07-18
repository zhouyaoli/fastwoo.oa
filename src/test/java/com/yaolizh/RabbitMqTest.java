//package com.yaolizh;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.yaolizh.rabbitmq.sender.Sender;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = FastwooMonitorApplication.class)
//public class RabbitMqTest {
//	private static Logger logger = LoggerFactory.getLogger(RabbitMqTest.class);
//	@Autowired
//	private Sender sender;
//
//	@Test
//	public void testSerialData() throws Exception {
//		sender.testSerialData();
//	}
//	/**
//	 * 调用生产者进行消息发送
//	 */
//	@Test
//	public void hello() throws Exception {
//		sender.send();
//	}
//
//	/**
//	 * 发送消息
//	 *
//	 * @param message
//	 *            消息内容 说明: routingKey可以指定也可以不指定，这里我们给一个空字符串""
//	 */
//	@Test
//	public void sendFanoutMessage() {
//		Object message = "你好sendFanoutMessage：";
//		logger.info("【消息发送者】发送消息到fanout交换机，消息内容为: {}", message);
//		sender.sendFanoutMessage(message);
//	}
//
//	@Test
//	public void sendExchangeNameMessage() {
//		Object message = "你好sendExchangeNameMessage：";
//		logger.info("【消息发送者】发送消息到sendExchangeNameMessage交换机，消息内容为: {}", message);
//		sender.sendExchangeNameMessage(message);
//
//	}
//
//	@Test
//	public void sendTopExchangeMessage() {
//		Object message = "你好sendTopExchangeMessage：";
//		logger.info("【消息发送者】发送消息到sendTopExchangeMessage交换机，消息内容为: {}", message);
//		sender.sendTopExchangeMessage(message);
//	}
//
//}