/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package point.of.sale.system;

import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class CustomComboBoxRenderer extends DefaultListCellRenderer {

    private String promptText;

    public CustomComboBoxRenderer(String promptText) {
        this.promptText = promptText;
    }

    @Override
    public Component getListCellRendererComponent(
            JList<?> list,
            Object value, 
            int index,
            boolean isSelected, 
            boolean cellHasFocus) {
        
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value != null && value.equals(promptText)) {
            // Apply custom font and color to the prompt
            setFont(getFont().deriveFont(Font.ITALIC));
            setForeground(Color.GRAY);
        } else {
            // Reset to default font and color for other items
            setFont(list.getFont());
            setForeground(list.getForeground());
        }

        return this;
    }
}
