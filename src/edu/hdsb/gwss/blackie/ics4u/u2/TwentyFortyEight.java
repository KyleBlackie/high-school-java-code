/*
 * Name: Kyle Blackie
 * Date: March 05 ,2017
 * Description: The Game 2048
 */
package edu.hdsb.gwss.blackie.ics4u.u2;

import java.awt.event.KeyEvent;
import javax.swing.JLabel;

/**
 *
 * @author 1blackiekyl
 */
public class TwentyFortyEight extends javax.swing.JFrame {

    private JLabel[][] squareLabels;
    private int[][] squareValues;
    private int score;

    /**
     * Creates new form TwentyFortyEight
     */
    public TwentyFortyEight() {
        initComponents();
        //VALUES OF ALL THE SQUARES
        squareValues = new int[4][4];
        //PUT JLabels into 2d Array
        squareLabels = new JLabel[4][4];
        squareLabels[0][0] = jLabel00;
        squareLabels[0][1] = jLabel01;
        squareLabels[0][2] = jLabel02;
        squareLabels[0][3] = jLabel03;
        squareLabels[1][0] = jLabel10;
        squareLabels[1][1] = jLabel11;
        squareLabels[1][2] = jLabel12;
        squareLabels[1][3] = jLabel13;
        squareLabels[2][0] = jLabel20;
        squareLabels[2][1] = jLabel21;
        squareLabels[2][2] = jLabel22;
        squareLabels[2][3] = jLabel23;
        squareLabels[3][0] = jLabel30;
        squareLabels[3][1] = jLabel31;
        squareLabels[3][2] = jLabel32;
        squareLabels[3][3] = jLabel33;

        //REAL GAME PLAY  
        //newGame();
        //TESTING
        squareValues = new int[][]{
            {2, 0, 0, 0},
            {2, 0, 2, 0},
            {0, 2, 0, 2},
            {2, 2, 0, 2}
        };
        refreshTiles();

    }

    private void newGame() {
        //1. RESET SCORE
        score = 0;

        //2. CLEAR SQUARE VALUES
        for (int r = 0; r < squareValues.length; r++) {
            for (int c = 0; c < squareValues[r].length; c++) {
                squareValues[r][c] = 0;
            }
        }
        //3. PICK 2 RANDOM SQUARES AND PLACE A 2 IN EACH
        placeRandomTwo();
        placeRandomTwo();

        //4. UPDATE AND RENDER TILES
        refreshTiles();
    }

    private void placeRandomTwo() {
        boolean placed = false;
        int r, c;
        while (!placed) {
            r = (int) (Math.random() * squareValues.length);
            c = (int) (Math.random() * squareValues.length);

            //EMPTY?
            if (squareValues[r][c] == 0) {
                squareValues[r][c] = 2;
                placed = true;
            }
        }
        refreshTiles();
    }

    private void refreshTiles() {
        for (int r = 0; r < squareValues.length; r++) {
            for (int c = 0; c < squareValues[r].length; c++) {

                // ZERO?
                if (squareValues[r][c] == 0) {
                    squareLabels[r][c].setText("");
                    //squareLabels[r][c].setBackground(Color.WHITE);
                } else {
                    squareLabels[r][c].setText("" + squareValues[r][c]);
                    //squareLabels[r][c].setBackground(Color.getHSBColor(100, 250, 70));
                }
            }
        }
    }

    private void swap(int row, int column, int row2, int column2) {
        squareValues[row2][column2] = squareValues[row][column];
        squareValues[row][column] = 0;
    }

    //SLIDE FUNCTIONS-----------------------------------------------------------
    private void slideDown() {
        //PASS FOR BUBBLE SORT
        for (int pass = 0; pass < squareValues.length - 1; pass++) {
            //START AT SECOND TO LAST ROW
            for (int r = squareValues.length - 2; r >= 0 + pass; r--) {
                for (int c = 0; c > squareValues[r].length; c++) {
                    //IS BOTTOM EMPTY?
                    if (squareValues[r][c] != 0 && squareValues[r + 1][c] == 0) {
                        //SWAP
                        swap(r, c, r + 1, c);
                    }
                }
            }
        }
    }

    private void slideUp() {

    }

    private void slideRight() {

        //FOR EACH ROW
        for (int r = 0; r < squareValues.length; r++) {

            //PASS FOR BUBBLE SORT
            for (int pass = 0; pass < squareValues[r].length - 1; pass++) {

                //START AT SECOND COLUMN
                for (int c = squareValues[r].length - 2; c >= 0 + pass; c--) {

                    //IS RIGHT EMPTY?
                    if (squareValues[r][c] != 0 && squareValues[r][c + 1] == 0) {

                        //SWAP
                        swap(r, c, r, c + 1);

                    }

                }

            }

        }
    }

    private void slideLeft() {

        //FOR EACH ROW
        for (int r = 0; r < squareValues.length; r++) {

            //PASS FOR BUBBLE SORT
            for (int pass = 0; pass < squareValues[r].length - 1; pass++) {

                //START AT SECOND COLUMN
                for (int c = 1; c < squareValues[r].length - pass; c++) {

                    //IS LEFT EMPTY?
                    if (squareValues[r][c] != 0 && squareValues[r][c - 1] == 0) {

                        //SWAP
                        swap(r, c, r, c - 1);

                    }

                }

            }

        }

    }

    //MERGE FUNCTIONS-----------------------------------------------------------
    private void mergeRight() {

        //FOR EACH ROW
        for (int r = 0; r < squareValues.length; r++) {

            //START AT SECOND COLUMN
            for (int c = squareValues[r].length - 2; c >= 0; c--) {
                if (squareValues[r][c] == squareValues[r][c + 1] && squareValues[r][c] != 0) {
                    squareValues[r][c + 1] *= 2;
                    score += squareValues[r][c];
                    squareValues[r][c] = 0;
                }

            }

        }
    }

    private void mergeLeft() {
        //FOR EACH ROW
        for (int r = 0; r < squareValues.length; r++) {

            //START AT SECOND COLUMN
            for (int c = 1; c < squareValues[r].length; c++) {
                if (squareValues[r][c] == squareValues[r][c - 1]) {
                    squareValues[r][c] *= 2;
                    squareValues[r][c - 1] = 0;
                }

            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel jPanelMain = new javax.swing.JPanel();
        jLabel00 = new javax.swing.JLabel();
        jLabel01 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel02 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel03 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanelTop = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanelMain.setBackground(new java.awt.Color(106, 47, 43));
        jPanelMain.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jLabel00.setBackground(new java.awt.Color(209, 137, 72));
        jLabel00.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel00.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel00.setText("2048");
        jLabel00.setOpaque(true);

        jLabel01.setBackground(new java.awt.Color(209, 137, 72));
        jLabel01.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel01.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel01.setText("2048");
        jLabel01.setOpaque(true);

        jLabel31.setBackground(new java.awt.Color(209, 137, 72));
        jLabel31.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("2048");
        jLabel31.setOpaque(true);

        jLabel21.setBackground(new java.awt.Color(209, 137, 72));
        jLabel21.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("2048");
        jLabel21.setOpaque(true);

        jLabel11.setBackground(new java.awt.Color(209, 137, 72));
        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("2048");
        jLabel11.setOpaque(true);

        jLabel30.setBackground(new java.awt.Color(209, 137, 72));
        jLabel30.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("2048");
        jLabel30.setOpaque(true);

        jLabel20.setBackground(new java.awt.Color(209, 137, 72));
        jLabel20.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("2048");
        jLabel20.setOpaque(true);

        jLabel10.setBackground(new java.awt.Color(209, 137, 72));
        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("2048");
        jLabel10.setOpaque(true);

        jLabel02.setBackground(new java.awt.Color(209, 137, 72));
        jLabel02.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel02.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel02.setText("2048");
        jLabel02.setOpaque(true);

        jLabel12.setBackground(new java.awt.Color(209, 137, 72));
        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("2048");
        jLabel12.setOpaque(true);

        jLabel22.setBackground(new java.awt.Color(209, 137, 72));
        jLabel22.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("2048");
        jLabel22.setOpaque(true);

        jLabel32.setBackground(new java.awt.Color(209, 137, 72));
        jLabel32.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("2048");
        jLabel32.setOpaque(true);

        jLabel03.setBackground(new java.awt.Color(209, 137, 72));
        jLabel03.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel03.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel03.setText("2048");
        jLabel03.setOpaque(true);

        jLabel13.setBackground(new java.awt.Color(209, 137, 72));
        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("2048");
        jLabel13.setOpaque(true);

        jLabel23.setBackground(new java.awt.Color(209, 137, 72));
        jLabel23.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("2048");
        jLabel23.setOpaque(true);

        jLabel33.setBackground(new java.awt.Color(209, 137, 72));
        jLabel33.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("2048");
        jLabel33.setOpaque(true);

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addComponent(jLabel00, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel01, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel02, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel03, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addComponent(jLabel03, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addComponent(jLabel02, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel00, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel01, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabelTitle.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("2048");

        javax.swing.GroupLayout jPanelTopLayout = new javax.swing.GroupLayout(jPanelTop);
        jPanelTop.setLayout(jPanelTopLayout);
        jPanelTopLayout.setHorizontalGroup(
            jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelTopLayout.setVerticalGroup(
            jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTopLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jPanelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(150, 150, 150))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(52, Short.MAX_VALUE)
                        .addComponent(jPanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        int keyCode = evt.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                System.out.println("UP");
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("DOWN");
                // handle down 
                slideDown();

                break;
            case KeyEvent.VK_LEFT:
                // handle left
                System.out.println("LEFT");
                slideLeft();
                mergeLeft();
                slideLeft();
                placeRandomTwo();

                //isGameOver();
                break;
            case KeyEvent.VK_RIGHT:
                // handle right
                System.out.println("RIGHT");
                slideRight();
                mergeRight();
                slideRight();
                placeRandomTwo();
                //isGameOver();
                break;
        }
    }//GEN-LAST:event_formKeyPressed

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
            java.util.logging.Logger.getLogger(TwentyFortyEight.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TwentyFortyEight.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TwentyFortyEight.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TwentyFortyEight.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TwentyFortyEight().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel00;
    private javax.swing.JLabel jLabel01;
    private javax.swing.JLabel jLabel02;
    private javax.swing.JLabel jLabel03;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JPanel jPanelTop;
    // End of variables declaration//GEN-END:variables
}
