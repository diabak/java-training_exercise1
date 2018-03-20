package treat_zadanie_KIMI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class treat_main extends JFrame implements ActionListener {

	JButton stop = new JButton();
	JTextArea output = new JTextArea(25, 40);
	int k = 0;
	int n = 15;
	JButton treat1 = new JButton();
	JButton treat2 = new JButton();
	JButton treat3 = new JButton();
	JButton treat4 = new JButton();
	JFrame frame = new JFrame();

	treat_main() {
		frame.setSize(500, 500);
		String s_treat1 = "Thread 1";
		treat1.setSize(100, 100);
		treat1.setVisible(true);
		treat1.setText(s_treat1);
		treat1.setActionCommand(s_treat1);
		treat1.addActionListener((ActionListener) this);
		frame.add(treat1);
		String s_treat2 = "Thread 2";
		treat2.setSize(100, 100);
		treat2.setVisible(true);
		treat2.setText(s_treat2);
		treat2.setActionCommand(s_treat2);
		treat2.addActionListener((ActionListener) this);
		frame.add(treat2);
		String s_treat3 = "Thread 3";
		treat3.setSize(100, 100);
		treat3.setVisible(true);
		treat3.setText(s_treat3);
		treat3.setActionCommand(s_treat3);
		treat3.addActionListener((ActionListener) this);
		frame.add(treat3);
		String s_treat4 = "Thread 4";
		treat4.setSize(100, 100);
		treat4.setVisible(true);
		treat4.setText(s_treat4);
		treat4.setActionCommand(s_treat4);
		treat4.addActionListener((ActionListener) this);
		frame.add(treat4);
		stop.setSize(100, 100);
		stop.setVisible(true);
		stop.setText("Stop");
		stop.setActionCommand("Stop");
		stop.addActionListener((ActionListener) this);
		frame.add(stop);
		output.setVisible(true);
		JScrollPane scroll = new JScrollPane(output);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frame.add(scroll);
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		JButton o = (JButton) e.getSource();
		String s_cmd = o.getText();
		if (s_cmd.startsWith("Thread")) {
			String arr[] = s_cmd.split(" ", 2);
			s_cmd = arr[0];
			k = Integer.parseInt(arr[1]);
			o.setText("Suspend");
		} else if (s_cmd.startsWith("Suspend")) {
			o.setText("Continue");
		} else if (s_cmd.startsWith("Continue")) {
			o.setText("Suspend");
		}
		try {
			System.out.println("s_cmd: " + s_cmd);
			Method m_method = this.getClass().getDeclaredMethod("task" + s_cmd);
			m_method.invoke(this);
		} catch (Exception exc) {
			System.out.println("error in action performed");
			exc.printStackTrace();
		}
	}

	class SumTask implements Callable<Integer> {

		private int taskNum, limit, i_min, i_max;

		public SumTask(int taskNum, int limit, int i_min, int i_max) {
			this.taskNum = taskNum;
			this.limit = limit;
			this.i_min = i_min;
			this.i_max = i_max;
		}

		public Integer call() throws Exception {
			int sum = 0;
			Random time_random = new Random();
			int i_wait = time_random.nextInt(1000);
			while (sum < limit) {
				Random random = new Random();
				if (Thread.currentThread().isInterrupted()) {
					return null;
				}
				int random_numer = random.nextInt(i_max) + i_min;
				sum = sum + random_numer;
				output.append(
						"Thread " + taskNum + " (limit = " + limit + "): " + random_numer + ", sum = " + sum + '\n');
				Thread.sleep(i_wait);
			}
			if (k == 1) {
				treat1.setEnabled(false);
				treat1.setText("DONE");
			} else if (k == 2) {
				treat2.setEnabled(false);
				treat2.setText("DONE");
			} else if (k == 3) {
				treat3.setEnabled(false);
				treat3.setText("DONE");
			} else if (k == 4) {
				treat4.setEnabled(false);
				treat4.setText("DONE");
			}
			output.append("Thread " + k + ": Done!\n");
			return sum;
		}
	};

	Future<Integer> task;

	ExecutorService exec = Executors.newFixedThreadPool(3);

	public void taskThread() {
		try {
			task = exec.submit(new SumTask(k, k * 100, 1, 10));
		} catch (RejectedExecutionException exc) {
			output.append("Execution rejected\n");
			return;
		}
	}

	public void taskSuspend() {
		try {
			synchronized (this) {
				this.wait();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void taskContinue() {
		try {
			// task = exec.submit(new SumTask(k, k * 100, 1, 10));
		} catch (RejectedExecutionException exc) {
			output.append("Execution rejected\n");
			return;
		}
		// output.append("Task " + k + " submitted\n");
	}

	public void taskStop() {
		List<Runnable> awaiting = exec.shutdownNow();
		output.append("Stop all task!:\n");
		for (Runnable r : awaiting) {
			output.append(r.getClass().getName() + '\n');
		}
		treat1.setEnabled(false);
		treat1.setText("DONE");
		treat2.setEnabled(false);
		treat2.setText("DONE");
		treat3.setEnabled(false);
		treat3.setText("DONE");
		treat4.setEnabled(false);
		treat4.setText("DONE");
	}

	public static void main(String[] args) {
		new treat_main();
	}
}
