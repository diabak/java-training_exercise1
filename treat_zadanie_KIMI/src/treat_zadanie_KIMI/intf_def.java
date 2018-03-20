package treat_zadanie_KIMI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class intf_def extends JFrame implements ActionListener {

	JButton stop = new JButton();
	JTextArea output = new JTextArea(25, 40);
	Integer k = 0;
	boolean isCPRessed = false;
	int n = 15;
	JFrame frame = new JFrame();
	int amountOfThreads = 25;
	JButton[] arrayButtons = new JButton[amountOfThreads];

	intf_def() {
		frame.setLayout(new BorderLayout());
		JPanel buttons = new JPanel();
		cKeyListener listener = new cKeyListener();

		for (int i = 0; i < amountOfThreads; i++) {
			JButton temporaryButton = new JButton();
			String name = "Thread " + (i + 1);
			temporaryButton.setVisible(true);
			temporaryButton.setText(name);
			temporaryButton.setActionCommand(name);
			temporaryButton.addActionListener((ActionListener) this);
			temporaryButton.addKeyListener(listener);
			buttons.add(temporaryButton);
			arrayButtons[i] = temporaryButton;
		}
		JScrollPane scrollButtons = new JScrollPane(buttons, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollButtons.setPreferredSize(new Dimension(500, 50));
		frame.add(scrollButtons, BorderLayout.NORTH);
		stop.setSize(100, 100);
		stop.setVisible(true);
		stop.setText("Stop");
		stop.setActionCommand("Stop");
		stop.addActionListener((ActionListener) this);
		frame.add(stop, BorderLayout.SOUTH);
		output.addKeyListener(listener);
		output.setVisible(true);
		output.setEditable(false);
		JScrollPane scroll = new JScrollPane(output);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frame.add(scroll, BorderLayout.CENTER);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();

	}

	class cKeyListener extends KeyAdapter {

		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == 'c')
				isCPRessed = true;
		}

		public void keyReleased(KeyEvent evt) {
			isCPRessed = false;
		}
	}

	public void actionPerformed(ActionEvent e) {

		JButton o = (JButton) e.getSource();
		String s_cmd = o.getText();
		if (!s_cmd.startsWith("Stop")) {

			String arr[] = s_cmd.split(" ", 2);
			s_cmd = arr[0];

			k = Integer.parseInt(arr[1]);

			if (s_cmd.startsWith("Thread")) {
				o.setText("Suspend " + k);
			} else if (isCPRessed) {
				taskCancel(k);
			} else if (s_cmd.startsWith("Suspend")) {
				o.setText("Continue " + k);
			} else if (s_cmd.startsWith("Continue")) {
				o.setText("Suspend " + k);
			}
			try {
				Method m_method = this.getClass().getDeclaredMethod("task" + s_cmd, Integer.class);
				m_method.invoke(this, k);
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		} else
			this.taskStop();
	}

	class SumTask implements Callable<Integer> {

		private int taskNum, limit, i_min, i_max;
		private boolean waitForThread = false;

		public SumTask(int taskNum, int limit, int i_min, int i_max) {
			this.taskNum = taskNum;
			this.limit = limit;
			this.i_min = i_min;
			this.i_max = i_max;
		}

		public void waitForThread() {
			waitForThread = true;
		}

		public void goTo() {
			waitForThread = false;
			synchronized (this) {
				this.notify();
			}
		}

		public void cancelThread() {
			Thread.currentThread().interrupt();
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
				if (waitForThread)
					synchronized (this) {
						this.wait();
					}
			}
			arrayButtons[k - 1].setEnabled(false);
			arrayButtons[k - 1].setText("Done");
			output.append("Thread " + k + ": Done!\n");
			return sum;
		}
	};

	Future<Integer> task;

	ExecutorService exec = Executors.newFixedThreadPool(amountOfThreads); // ILOSC
	SumTask aaa = null;
	SumTask[] array = new SumTask[amountOfThreads]; // ILOSC

	public void taskThread(Integer threadNo) {
		array[threadNo - 1] = new SumTask(k, k * 100, 1, 10);
		try {
			task = exec.submit(array[threadNo - 1]);
		} catch (RejectedExecutionException exc) {
			output.append("Execution rejected\n");
			return;
		}
	}

	private Object lock = new Object();

	public synchronized void taskSuspend(Integer threadNo) {
		synchronized (lock) {
			try {

				array[threadNo - 1].waitForThread();
				// task.get();

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}

	public void taskContinue(Integer threadNo) {
		synchronized (lock) {
			try {
				array[threadNo - 1].goTo();
				// task = exec.submit(new SumTask(k, k * 100, 1, 10));
			} catch (RejectedExecutionException exc) {
				output.append("Execution rejected\n");
				return;
			}
		}
		// output.append("Task " + k + " submitted\n");
	}

	public void taskCancel(Integer threadNo) {
		array[threadNo - 1].cancelThread();
		arrayButtons[threadNo - 1].setEnabled(false);
		arrayButtons[threadNo - 1].setText("Cancelled");
	}

	public void taskStop() {
		List<Runnable> awaiting = exec.shutdownNow();
		output.append("Stop all tasks!:\n");
		for (Runnable r : awaiting) {
			output.append(r.getClass().getName() + '\n');
		}
		for (JButton button : arrayButtons) {
			button.setEnabled(false);
			if (button.getText().startsWith("Suspend") || button.getText().startsWith("Continue"))
				button.setText("Done");
		}
		stop.setEnabled(false);

	}

	public static void main(String[] args) {
		new intf_def();
	}
}