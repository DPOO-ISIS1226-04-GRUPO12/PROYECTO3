package consola;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

public class Calendario extends JFrame implements ActionListener {
    private DefaultTableModel model;
    private JTable table;
    private JButton prevButton, nextButton;
    private JComboBox<Integer> yearComboBox;
    private JComboBox<String> monthComboBox;
    private JLabel monthLabel;
    private Map<Date, Integer> map;

    public Calendario(Map<Date, Integer> map) {
    	this.map =map;
        setTitle("Calendario");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        prevButton = new JButton("Anterior");
        nextButton = new JButton("Siguiente");
        yearComboBox = new JComboBox<>(getYearArray());
        monthComboBox = new JComboBox<>(getMonthArray());
        monthLabel = new JLabel();

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(prevButton, BorderLayout.WEST);
        panel.add(yearComboBox, BorderLayout.CENTER);
        panel.add(monthComboBox, BorderLayout.EAST);
        panel.add(nextButton, BorderLayout.EAST);
        panel.add(monthLabel, BorderLayout.SOUTH);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        updateCalendar();

        prevButton.addActionListener(this);
        nextButton.addActionListener(this);
        yearComboBox.addActionListener(this);
        monthComboBox.addActionListener(this);

        setVisible(true);
    }

    private Integer[] getYearArray() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        Integer[] years = new Integer[31]; // Puedes ajustar este rango según tus necesidades hijo
        for (int i = 0; i < 31; i++) {
            years[i] = currentYear - 15 + i;
        }
        return years;
    }

    private String[] getMonthArray() {
        return new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    }

    private void updateCalendar() {
        int selectedYear = (int) yearComboBox.getSelectedItem();
        int selectedMonth = monthComboBox.getSelectedIndex();
        Calendar calendar = new GregorianCalendar(selectedYear, selectedMonth, 1);

        String[] columns = {"Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb"};
        model.setColumnIdentifiers(columns);

        int startDay = getStartDay(selectedYear, selectedMonth);
        int numberOfDays = getNumberOfDays(selectedYear, selectedMonth);

        model.setRowCount(0);

        int day = 1;
       for (Map.Entry<Date, Integer> entry : map.entrySet()) {
            System.out.println("Fecha: " + entry.getKey() + ", Número44: " + entry.getValue());
        }
        for (int i = 0; i < 6; i++) {
            Object[] row = new Object[7];
            for (int j = 0; j < 7; j++) {
                if ((i == 0 && j < startDay) || (day > numberOfDays)) {
                    row[j] = "";
                } else {
                    row[j] = day;

                    Calendar currentCalendar = new GregorianCalendar(selectedYear, selectedMonth, day, 0, 0, 0);
                    Date currentDate = currentCalendar.getTime();

                    if (map.containsKey(currentDate)) {
                    	

                    	int value = map.getOrDefault(currentDate, 0);
                    	System.out.println("Contenido del calendario:");
                        
                    
                        Color backgroundColor = calculateColor(value);
                        table.getColumnModel().getColumn(j).setCellRenderer(new CustomRenderer(backgroundColor));
                    }
                    day++;
                }
            }
            model.addRow(row);
        }

        monthLabel.setText(getMonthArray()[selectedMonth] + " " + selectedYear);
    }

    private Color calculateColor(int value) {
      
        float normalizedValue = Math.min(1.0f, value / 10.0f); 

     
        float hue = 0.3f; 
        float saturation = 1.0f;
        float brightness = 0.5f + 0.5f * normalizedValue;
        Color color = Color.getHSBColor(hue, saturation, brightness);
        
        //System.out.println("Value: " + value + " Color: " + color);
        return color;
    }

    
    private static class CustomRenderer extends DefaultTableCellRenderer {
        private final Color backgroundColor;

        public CustomRenderer(Color backgroundColor) {
            this.backgroundColor = backgroundColor;
        }

        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setBackground(backgroundColor);
            return this;
        }
    }

    private int getStartDay(int year, int month) {
        Calendar calendar = new GregorianCalendar(year, month, 1);
        int startDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return (startDay + 7) % 7;
    }

    private int getNumberOfDays(int year, int month) {
        Calendar calendar = new GregorianCalendar(year, month, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    private void previousMonth() {
        int currentMonth = monthComboBox.getSelectedIndex();
        if (currentMonth == 0) {
            monthComboBox.setSelectedIndex(11);
            yearComboBox.setSelectedItem((int) yearComboBox.getSelectedItem() - 1);
        } else {
            monthComboBox.setSelectedIndex(currentMonth - 1);
        }
        updateCalendar();
    }

    private void nextMonth() {
        int currentMonth = monthComboBox.getSelectedIndex();
        if (currentMonth == 11) {
            monthComboBox.setSelectedIndex(0);
            yearComboBox.setSelectedItem((int) yearComboBox.getSelectedItem() + 1);
        } else {
            monthComboBox.setSelectedIndex(currentMonth + 1);
        }
        updateCalendar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == prevButton) {
            previousMonth();
        } else if (e.getSource() == nextButton) {
            nextMonth();
        } else if (e.getSource() == yearComboBox || e.getSource() == monthComboBox) {
            updateCalendar();
        }
    }

}