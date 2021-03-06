package com.lbg.c2;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class C2Frame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Plugin plugin;
	private JList<String> conditions;
	private JList<String> actions;
	private JList<String> expressions;
	private JList<String> properties;
	private JTabbedPane pane;
	private C2Bar bar;
	private C2Popup popup;
	private InfoFrame infoFrame;
	private ParamFrame paramFrame;
	
	public C2Frame () {
		
		try {
		   UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
			| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		
		Translator.init();
		
		this.setTitle(Translator.getTitle());
		
		plugin = new Plugin ();
		
		infoFrame = new InfoFrame(this);
		
		paramFrame = new ParamFrame();
		
		
		
		
		conditions = new JList<String> ();
		actions = new JList<String> ();
		expressions = new JList<String>();
		properties = new JList<String> ();
		conditions.setToolTipText("Conditions");
		actions.setToolTipText("Actions");
		expressions.setToolTipText("Expressions");
		properties.setToolTipText("Properties");
		
		
		
		
		pane = new JTabbedPane ();
		pane.addTab(Translator.getConditions(), new JScrollPane(conditions));
		pane.addTab(Translator.getActions(), new JScrollPane(actions));
		pane.addTab(Translator.getExpressions(), new JScrollPane(expressions));
		pane.addTab(Translator.getProperties(), new JScrollPane(properties));
		
		popup = new C2Popup (this,pane);
		
		conditions.addMouseListener(new MouseAdapter () {
			public void mousePressed (MouseEvent e) {
				checkForTrigger(e);
			}
			
			public void mouseReleased (MouseEvent e) {
				checkForTrigger(e);
			}
			
			private void checkForTrigger (MouseEvent e) {
				if (e.isPopupTrigger()) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		
		actions.addMouseListener(new MouseAdapter () {
			public void mousePressed (MouseEvent e) {
				checkForTrigger(e);
			}
			
			public void mouseReleased (MouseEvent e) {
				checkForTrigger(e);
			}
			
			private void checkForTrigger (MouseEvent e) {
				if (e.isPopupTrigger()) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		
		expressions.addMouseListener(new MouseAdapter () {
			public void mousePressed (MouseEvent e) {
				checkForTrigger(e);
			}
			
			public void mouseReleased (MouseEvent e) {
				checkForTrigger(e);
			}
			
			private void checkForTrigger (MouseEvent e) {
				if (e.isPopupTrigger()) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		
		properties.addMouseListener(new MouseAdapter () {
			public void mousePressed (MouseEvent e) {
				checkForTrigger(e);
			}
			
			public void mouseReleased (MouseEvent e) {
				checkForTrigger(e);
			}
			
			private void checkForTrigger (MouseEvent e) {
				if (e.isPopupTrigger()) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		
		pane.addChangeListener(new ChangeListener () {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (pane.getSelectedIndex() == C2Popup.COND || pane.getSelectedIndex() == C2Popup.ACT ) {
					bar.getParam().setEnabled(true);
				}
				else {
					bar.getParam().setEnabled(false);
				}
				
			}
			
		});
		
		this.add(pane,BorderLayout.CENTER);
		
		
		bar = new C2Bar (this,conditions,actions,expressions,properties);
		
		this.setJMenuBar(bar);
		
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public JList<String> getConditions () {
		return conditions;
	}
	
	public JList<String> getActions () {
		return actions;
	}
	
	public JList<String> getExpressions () {
		return expressions;
	}
	
	public JList<String> getProperties () {
		return properties;
	}

	public Plugin getPlugin() {
		return plugin;
	}

	public void setPlugin(Plugin plugin) {
		this.plugin = plugin;
	}
	
	public C2Bar getBar  () {
		return bar;
	}
	
	public void update () {
		
		plugin = bar.getPlugin();
		
		String con [] = new String [plugin.getConditions().size()];
		for (int i = 0; i < plugin.getConditions().size();i++) {
		 con[i] = plugin.getConditions().get(i).toString();
		}
		
		conditions.setListData(con);
		
		String act [] = new String [plugin.getActions().size()];
		for (int i = 0; i < plugin.getActions().size();i++) {
		 act[i] = plugin.getActions().get(i).toString();
		}
		actions.setListData(act);
		
		String exp [] = new String[plugin.getExpressions().size()];
		for (int i = 0; i < plugin.getExpressions().size();i++) {
			 exp[i] = plugin.getExpressions().get(i).toString();
			}
		
		expressions.setListData(exp);
		
		String prop [] = new String[plugin.getProperties().size()];
		for (int i = 0; i < plugin.getProperties().size(); i++) {
			prop[i] = plugin.getProperties().get(i).toString();
		}
		
		properties.setListData(prop);
		
	}

	public InfoFrame getInfoFrame() {
		return infoFrame;
	}

	public void setInfoFrame(InfoFrame infoFrame) {
		this.infoFrame = infoFrame;
	}

	public ParamFrame getParamFrame() {
		return paramFrame;
	}

	public void setParamFrame(ParamFrame paramFrame) {
		this.paramFrame = paramFrame;
	}
	
	public JTabbedPane getPane () {
		return pane;
	}
	
	public C2Popup getPopup () {
		return popup;
	}
	
	public void refresh () {
		pane.setTitleAt(0, Translator.getConditions());
		pane.setTitleAt(1, Translator.getActions());
		pane.setTitleAt(2, Translator.getExpressions());
		pane.setTitleAt(3, Translator.getProperties());
		
		this.setTitle(Translator.getTitle());
	}
	

}
