package main.represent;

import main.criteria.Criteriable;
import main.criteria.CubicProgressiveCriteria;
import main.criteria.LinearCriteria;
import static main.Parameters.SCREEN_SIZE;
import main.criteria.ProgressiveCriteria;
import main.criteria.UserCriteria;

/**
 *
 * @author Алексей
 */
public class CriteriaForm extends javax.swing.JDialog {

    private final int minValue = 20;
    private final int maxValue = 100;

    public CriteriaForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                SCREEN_SIZE.height / 2 - getHeight() / 2);
        setActiveComponents(false);
        spinnerForFive.setValue(85);
        spinnerForFour.setValue(65);
        spinnerForThree.setValue(50);
    }

    private void setActiveComponents(boolean active) {
        spinnerForFive.setEnabled(active);
        spinnerForFour.setEnabled(active);
        spinnerForThree.setEnabled(active);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        rbLinearScale = new javax.swing.JRadioButton();
        rbProgressiveSoft = new javax.swing.JRadioButton();
        rbProgressiveHard = new javax.swing.JRadioButton();
        rbCustom = new javax.swing.JRadioButton();
        spinnerForThree = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        spinnerForFour = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        spinnerForFive = new javax.swing.JSpinner();
        bChange = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Критерий оценки");

        buttonGroup1.add(rbLinearScale);
        rbLinearScale.setSelected(true);
        rbLinearScale.setText("линейная шкала");
        rbLinearScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbLinearScaleActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbProgressiveSoft);
        rbProgressiveSoft.setText("прогрессивная шкала(мягкая)");
        rbProgressiveSoft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbProgressiveSoftActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbProgressiveHard);
        rbProgressiveHard.setText("прогрессивная шкала(жесткая)");
        rbProgressiveHard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbProgressiveHardActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbCustom);
        rbCustom.setText("пользовательская");
        rbCustom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCustomActionPerformed(evt);
            }
        });

        spinnerForThree.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerForThreeStateChanged(evt);
            }
        });

        jLabel1.setText("Оценка: \"3\" от:");

        jLabel2.setText("Оценка: \"4\" от:");

        spinnerForFour.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerForFourStateChanged(evt);
            }
        });

        jLabel3.setText("Оценка: \"5\" от:");

        spinnerForFive.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerForFiveStateChanged(evt);
            }
        });

        bChange.setText("Изменить");
        bChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bChangeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(36, 36, 36)
                        .addComponent(spinnerForThree, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rbProgressiveHard)
                    .addComponent(rbProgressiveSoft)
                    .addComponent(rbLinearScale)
                    .addComponent(rbCustom)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(36, 36, 36)
                        .addComponent(spinnerForFour, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(36, 36, 36)
                        .addComponent(spinnerForFive, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bChange)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(rbLinearScale)
                .addGap(18, 18, 18)
                .addComponent(rbProgressiveSoft)
                .addGap(18, 18, 18)
                .addComponent(rbProgressiveHard)
                .addGap(18, 18, 18)
                .addComponent(rbCustom)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerForThree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerForFour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerForFive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(bChange)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbCustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCustomActionPerformed
        setActiveComponents(true);
    }//GEN-LAST:event_rbCustomActionPerformed

    public void setCriteria(Criteriable criteriable) {
        if (criteriable instanceof LinearCriteria) {
            rbLinearScale.setSelected(true);
            setActiveComponents(false);
        } else if (criteriable instanceof ProgressiveCriteria) {
            rbProgressiveSoft.setSelected(true);
            setActiveComponents(false);
        } else if (criteriable instanceof CubicProgressiveCriteria) {
            rbProgressiveHard.setSelected(true);
            setActiveComponents(false);
        } else {
            setActiveComponents(true);
            UserCriteria criteria = (UserCriteria) criteriable;
            rbCustom.setSelected(true);
            spinnerForThree.setValue(minValue);
            spinnerForFour.setValue(minValue + 1);
            spinnerForFive.setValue(minValue + 2);
            spinnerForFive.setValue(criteria.getScoredForFive());
            spinnerForFour.setValue(criteria.getScoredForFour());
            spinnerForThree.setValue(criteria.getScoredForThree());
        }
    }

    public Criteriable getCriteria() {
        if (rbCustom.isSelected()) {
            return new UserCriteria((int) spinnerForThree.getValue(),
                    (int) spinnerForFour.getValue(),
                    (int) spinnerForFive.getValue());
        }
        if (rbLinearScale.isSelected()) {
            return LinearCriteria.getInstance();
        }
        if (rbProgressiveSoft.isSelected()) {
            return ProgressiveCriteria.getInstance();
        }
        return CubicProgressiveCriteria.getInstance();
    }

    private void spinnerForFiveStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerForFiveStateChanged
        int value = (int) spinnerForFive.getValue();
        int valueFor4 = Math.max((int) spinnerForFour.getValue(), minValue);
        if (value > maxValue) {
            spinnerForFive.setValue(maxValue);
        } else if (value < valueFor4 + 1) {
            spinnerForFive.setValue(valueFor4 + 1);
        }
    }//GEN-LAST:event_spinnerForFiveStateChanged

    private void spinnerForFourStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerForFourStateChanged
        int value = (int) spinnerForFour.getValue();
        int valueFor5 = Math.max((int) spinnerForFive.getValue(), minValue);
        int valueFor3 = Math.max((int) spinnerForThree.getValue(), minValue);
        if (value > valueFor5 - 1) {
            spinnerForFour.setValue(valueFor5 - 1);
        } else if (value < valueFor3 + 1) {
            spinnerForFour.setValue(valueFor3 + 1);
        }
    }//GEN-LAST:event_spinnerForFourStateChanged

    private void spinnerForThreeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerForThreeStateChanged
        int value = (int) spinnerForThree.getValue();
        int valueFor4 = Math.max((int) spinnerForFour.getValue(), minValue);
        if (value > valueFor4 - 1) {
            spinnerForThree.setValue(valueFor4 - 1);
        } else if (value < minValue) {
            spinnerForThree.setValue(minValue);
        }
    }//GEN-LAST:event_spinnerForThreeStateChanged

    private void bChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bChangeActionPerformed
        dispose();
    }//GEN-LAST:event_bChangeActionPerformed

    private void rbLinearScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbLinearScaleActionPerformed
        setActiveComponents(false);
    }//GEN-LAST:event_rbLinearScaleActionPerformed

    private void rbProgressiveSoftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbProgressiveSoftActionPerformed
        setActiveComponents(false);
    }//GEN-LAST:event_rbProgressiveSoftActionPerformed

    private void rbProgressiveHardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbProgressiveHardActionPerformed
        setActiveComponents(false);
    }//GEN-LAST:event_rbProgressiveHardActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bChange;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton rbCustom;
    private javax.swing.JRadioButton rbLinearScale;
    private javax.swing.JRadioButton rbProgressiveHard;
    private javax.swing.JRadioButton rbProgressiveSoft;
    private javax.swing.JSpinner spinnerForFive;
    private javax.swing.JSpinner spinnerForFour;
    private javax.swing.JSpinner spinnerForThree;
    // End of variables declaration//GEN-END:variables

}
