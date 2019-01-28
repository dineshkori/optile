import java.util.TreeSet;

import com.optile.base.job.Job;
import com.optile.jobs.EmailManagerServiceImpl;

public class TestClass {

	public static void main(String[] args) {
		
		EmailManagerServiceImpl e1 =  new EmailManagerServiceImpl();
		e1.setCPUintensiveJob(false);
		e1.setName("Emailer 1");
		e1.setPriority(1);
		EmailManagerServiceImpl e2 =  new EmailManagerServiceImpl();
		e2.setCPUintensiveJob(false);
		e2.setName("Emailer 2");
		e2.setPriority(2);
		EmailManagerServiceImpl e3 =  new EmailManagerServiceImpl();
		e3.setCPUintensiveJob(false);
		e3.setName("Emailer 4");
		e3.setPriority(4);
		TreeSet<Job> Emailerlist = new TreeSet<Job>();
		Emailerlist.add(e3);
		Emailerlist.add(e2);
		Emailerlist.add(e1);
		
		
		System.out.println(Emailerlist);
		
	}
	
}
