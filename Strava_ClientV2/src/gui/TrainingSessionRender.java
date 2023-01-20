package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import data.dto.ChallengeDTO;

public class TrainingSessionRender extends JLabel implements TableCellRenderer {

	private static final long serialVersionUID = 1L;
	private ChallengeDTO selected = null;
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		this.setText(value.toString());
		this.setFont(new Font("Helvetica Bold", Font.PLAIN, 12));
		if(this.selected != null) {
			ChallengeDTO challenge = (ChallengeDTO) table.getModel().getValueAt(row, 4);
			if(selected.equals((ChallengeDTO) challenge)) {
				this.setOpaque(true);
				this.setBackground(new Color(186, 252, 103));
			}else {
				this.setOpaque(false);
			}
		}
		return this;
	}
	
	public void setChallenge(ChallengeDTO selected) {
		this.selected = selected;
	}
}
