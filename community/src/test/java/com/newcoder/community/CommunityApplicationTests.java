package com.newcoder.community;

import com.newcoder.community.dao.AlphaDao;
import com.newcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

//@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class CommunityApplicationTests implements ApplicationContextAware {
	private ApplicationContext applicationContext;
	@Autowired
	@Qualifier("alphaHibernate")
	private AlphaDao alphadao;
	@Autowired
	private AlphaService alphaService;
	@Autowired
	private  SimpleDateFormat simpleDateFormat;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Test
	public void testApplicationContext(){
        System.out.println(applicationContext);
        //通过接口类型获取bean
        AlphaDao alphadao = applicationContext.getBean(AlphaDao.class);
        System.out.println(alphadao.select());
/**    通过类名获取bean，如果不加后面的AlphaDao.class会怎么样？
 *  会得到一个Object类型的对象，要强制转型
 *  加了以后就直接得到AlphaDao.class类型了
 */
        alphadao = applicationContext.getBean("alphaHibernate", AlphaDao.class);
        System.out.println(alphadao.select());
    }

	@Test
	public void testBeanManagement(){
		AlphaService alphaservice=applicationContext.getBean(AlphaService.class);
		System.out.println(alphaservice);

		alphaservice=applicationContext.getBean(AlphaService.class);
		System.out.println(alphaservice);
	}

	@Test
	public void testBeanConfig(){
		SimpleDateFormat simpleDateFormat=
				applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	@Test
	public void testDI(){
		System.out.println(alphadao);
		System.out.println(alphaService);
		System.out.println(simpleDateFormat);
	}




}
