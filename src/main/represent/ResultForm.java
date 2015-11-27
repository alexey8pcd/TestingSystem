package main.represent;

import javax.swing.AbstractListModel;
import javax.swing.ListModel;
import main.criteria.Criteriable;
import main.criteria.LinearCriteria;
import static main.Parameters.SCREEN_SIZE;
import main.tasks.Variant;

/**
 *
 * @author Алексей
 */
public class ResultForm extends javax.swing.JDialog {

    private double result;
    private Variant[] answers;
    private Criteriable criteria;
    private final ListModel listModel = new AbstractListModel() {

        @Override
        public int getSize() {
            return answers.length;
        }

        @Override
        public Object getElementAt(int index) {
            Variant answer = answers[index];
            if (answer != null) {
                return answers[index].toString();
            } else {
                return "<html><font color=\"red\">(Нет ответа)";
            }
        }
    };

    private void refresh() {
        lMark.setText(String.valueOf(criteria.calculateMark(result)));
        lScore.setText((int) Math.round(result * 100) + "/100");
        lCriteriaName.setText(criteria.getName());
        listOfAnswers.updateUI();
    }

    public ResultForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                SCREEN_SIZE.height / 2 - getHeight() / 2);
        listOfAnswers.setModel(listModel);
        criteria = LinearCriteria.getInstance();
    }

    public void setData(double result, Variant[] answers) {
        this.result = result;
        this.answers = answers;
        refresh();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lScore = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listOfAnswers = new javax.swing.JList();
        lMark = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bChangeCriteria = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lCriteriaName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Результаты теста");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Набрано баллов:");

        lScore.setText("_______баллы");

        jLabel3.setText("Ваша оценка:");

        jScrollPane1.setViewportView(listOfAnswers);

        lMark.setText("________оценка");

        jLabel2.setText("Подробные результаты:");

        bChangeCriteria.setText("Изменить критерий оценки");
        bChangeCriteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bChangeCriteriaActionPerformed(evt);
            }
        });

        jLabel4.setText("Шкала:");

        lCriteriaName.setText("______критерий");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lMark, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                            .addComponent(lCriteriaName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 223, Short.MAX_VALUE)
                        .addComponent(bChangeCriteria)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lScore, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lMark, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lCriteriaName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(bChangeCriteria))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bChangeCriteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bChangeCriteriaActionPerformed
        CriteriaForm form = new CriteriaForm(null, true);
        form.setCriteria(criteria);
        form.setVisible(true);
        criteria = form.getCriteria();
        refresh();
    }//GEN-LAST:event_bChangeCriteriaActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bChangeCriteria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lCriteriaName;
    private javax.swing.JLabel lMark;
    private javax.swing.JLabel lScore;
    private javax.swing.JList listOfAnswers;
    // End of variables declaration//GEN-END:variables

}
