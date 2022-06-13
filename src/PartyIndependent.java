import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Beyza
 */
public class PartyIndependent extends javax.swing.JFrame {

    private String[] participant;

    public String[] getParticipant() {
        return this.participant;
    }

    public void setParticipant(String[] participant) {
        this.participant = participant;
    }

    public PartyIndependent(String[] participant) {
        this.participant = participant;
    }
     JPanel panel = new JPanel(new GridLayout(0,1,4,4));
     JScrollPane scrollpane;
     
     String[] candidateArray = new String[336];
     private JCheckBox boxes[];
    /**
     * Creates new form PartyIndependent
     */
    public PartyIndependent() {
        initComponents();
       
     // https://hajsoftutorial.com/jpanel-with-jscrollpane/
    //jp.add(scrollP,BorderLayout.CENTER);
        boxes = new JCheckBox[336];
        setContentPane(panel);
        scrollpane = new JScrollPane(panel);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollpane.setPreferredSize(new Dimension(300,300));
        setContentPane(scrollpane);
        panel.add(buttonVote);
        panel.add(buttonBack);
        setVisible(true);
        setResizable(true);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public String [] candidateFunction(String [] array){
        for(int i=0 ;i<336;i++){
            candidateArray[i] = array[i]; 
            createrCheckBox(i);   
    }

        return candidateArray;
    }
     public void createrCheckBox(int i) {
        boxes[i] = new JCheckBox(candidateArray[i]);
        panel.add(boxes[i]);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonVote = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Party Independent Voting");

        buttonVote.setText("Vote");
        buttonVote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVoteActionPerformed(evt);
            }
        });

        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(buttonVote, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(207, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(426, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonVote, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(buttonBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        // TODO add your handling code here:
        setVisible(false); 
        dispose(); //Destroy the JFrame object
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonVoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVoteActionPerformed
        // TODO add your handling code here:
        List <String> chosenCandidates = new ArrayList<>();
        String candidateNames = "";
        for (JCheckBox checkBox : boxes) {
            if (checkBox.isSelected()) {
                candidateNames= candidateNames + "-" + checkBox.getText();
            }
        }
         Vote userVote = new Vote("none",-1,this.participant,candidateNames);
        try {
            userVote.SubmitVote();
        } catch (IOException ex) {
            Logger.getLogger(CandidateSelection.class.getName()).log(Level.SEVERE, null, ex);
        }
        Main temp = new Main();
        temp.setVisible(true);
        temp.setState(java.awt.Frame.NORMAL);
    }//GEN-LAST:event_buttonVoteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PartyIndependent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PartyIndependent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PartyIndependent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PartyIndependent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PartyIndependent().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonVote;
    // End of variables declaration//GEN-END:variables
}