import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Brad Kivell
 *
 */
public class NoiseRemovingGUI extends javax.swing.JFrame {

    ImageProcess ip; // Stores reference to image process
    ImageIcon beforeImageIcon; // Stores before image
    ImageIcon afterImageIcon; // Stores after image

    public NoiseRemovingGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadButton = new javax.swing.JButton();
        removeNoiseButton = new javax.swing.JButton();
        afterImage = new javax.swing.JLabel();
        beforeImage = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        smoothFactorLabel = new javax.swing.JLabel();
        smoothFactorBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        loadButton.setText("Load File");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        removeNoiseButton.setText("Remove Noise");
        removeNoiseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeNoiseButtonActionPerformed(evt);
            }
        });

        afterImage.setText("[AFTER]");

        beforeImage.setText("[BEFORE]");

        saveButton.setText("Save File");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        smoothFactorLabel.setText("Smooth Factor");

        smoothFactorBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loadButton)
                        .addGap(77, 77, 77)
                        .addComponent(smoothFactorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(smoothFactorBox, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeNoiseButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(beforeImage, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(afterImage, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(beforeImage, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(afterImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(removeNoiseButton)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(smoothFactorBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(smoothFactorLabel))
                    .addComponent(loadButton)
                    .addComponent(saveButton))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        JFileChooser fileChooserWindow = new JFileChooser(new File(".")); // Creates file chooser window
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("", "png", "jpg"); // Creates file filter for png and jpg extensions only
        fileChooserWindow.setFileFilter(fileFilter); // Only allow image files
        int stateTxtFileChooser = fileChooserWindow.showOpenDialog(null);

        if (stateTxtFileChooser == JFileChooser.APPROVE_OPTION) { // If selected file is valid
            String fileName = fileChooserWindow.getSelectedFile().getPath(); // Retrieve path as string
            ip = new ImageProcess(fileName); // Starts image processing
            scaleImageToLabel(beforeImageIcon, beforeImage); // Displays GUI before image
        }
    }//GEN-LAST:event_loadButtonActionPerformed

    private void removeNoiseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeNoiseButtonActionPerformed
        if (ip != null) {
            int iterations = smoothFactorBox.getSelectedIndex() + 1; // Get value 1-5
            for (int i = 0; i < iterations; i++) {
                ip.cleanNoise(); // Cleans noise iterations times
            }
            scaleImageToLabel(afterImageIcon, afterImage); // Updates after image
        } else {
            JOptionPane.showMessageDialog(this, "No File has been uploaded");
        }
    }//GEN-LAST:event_removeNoiseButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        if (ip != null && ip.cleaned) { // Checks image has been cleaned
            ip.save("noise_removed.jpg"); // Saves image as input string
        } else {
            JOptionPane.showMessageDialog(this, "Please remove noise first");
        }

    }//GEN-LAST:event_saveButtonActionPerformed

    // Scales an image to fit on label
    private void scaleImageToLabel(ImageIcon imageIcon, JLabel imageLabel) {
        imageIcon = new ImageIcon(ip.buffered_image); // Creates imageIcon with old image
        Image downscaledImage = imageIcon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), java.awt.Image.SCALE_SMOOTH); // Downscales
        imageIcon = new ImageIcon(downscaledImage); // Creates icon with new image
        imageLabel.setIcon(imageIcon); // Sets image to new downscaled image
        imageLabel.setVisible(true); // Ensures visable
    }

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
            java.util.logging.Logger.getLogger(NoiseRemovingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NoiseRemovingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NoiseRemovingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NoiseRemovingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NoiseRemovingGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel afterImage;
    private javax.swing.JLabel beforeImage;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton removeNoiseButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox<String> smoothFactorBox;
    private javax.swing.JLabel smoothFactorLabel;
    // End of variables declaration//GEN-END:variables
}