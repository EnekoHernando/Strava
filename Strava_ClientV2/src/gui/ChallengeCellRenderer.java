package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ChallengeCellRenderer extends JLabel implements TableCellRenderer{
	private static final long serialVersionUID = 1L;
	private static SimpleDateFormat sdf2 = new SimpleDateFormat( "dd/MM/yyyy" );
	private String type = "All";
	public void setFilter(String type) {
		this.type = type;
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		this.setText(value.toString());
		this.setFont(new Font("Helvetica Bold", Font.PLAIN, 12));
		
		if(type.equals("Accepted")) {
			if(column == 4) {
				float i = (Float.parseFloat((String) value));
				JProgressBar label = new JProgressBar(0, 100);
				label.setValue((int)i);
				label.setStringPainted(true);
				label.setString(((float) Math.round((i*100))/100) + " %");
				return label;
			}
		}
		if(type.equals("All")) {
			if(column == 2) {
				JLabel label = new JLabel();
				String[] l = ((String) value).split(" - ");
				Date d = new Date(System.currentTimeMillis()-(86400*1000) );
				try {
					Date c = sdf2.parse(l[1]);
					if(c.before(d)) {
						label.setText((String) value);
						label.setOpaque(true);
						label.setBackground(new Color(255,0,0));
					}
					else {
						label.setText((String) value);
					}
					return label;
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if(column == 4) {
				JLabel label = new JLabel();
				if(((boolean) value)) {
					label.setText("Accepted");
					label.setOpaque(true);
					label.setBackground(new Color(0, 128, 0));
				}
				else {
					label.setText("Not Accepted");
					label.setOpaque(true);
					label.setBackground(new Color(255,0,0));
				}
				return label;
			}
		}
		return this;
	}

}
