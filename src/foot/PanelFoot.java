/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foot;

/**
 *
 * @author mooneswar.ramburrun
 */
public class PanelFoot extends javax.swing.JPanel implements Runnable {

    public PanelFoot() {
        java.awt.Toolkit kit;
        kit = java.awt.Toolkit.getDefaultToolkit();
        terrain = kit.getImage("terrain.gif");
        ballon = kit.getImage("ballon.gif");
    }

    public static int getNbAleat(int bInf, int bSup) {
        return (int) ((bSup - bInf + 1) * Math.random() + bInf);
    }

    public void demarrer() {
        if (th == null) {
            th = new Thread(this);
            th.start();
            x = XMAX / 2 - 15;
            y = YMAX / 2 - 15;
        }
    }

    @Override
    public void run() {
        Thread threadCourant;
        threadCourant = Thread.currentThread();
        while (th == threadCourant) {
            x += deltaX;
            y += deltaY;
            repaint();
    // série de tests

            if ((x > XBUT - 30) && (y > YBUT - 15) && (y < YBUT + 30)) {
                javax.swing.JOptionPane.showMessageDialog(this, "GOAL!!!", "BUT", javax.swing.JOptionPane.PLAIN_MESSAGE);
                this.recommencer();
            }  if ((x < 3) && (y > YBUT - 15) && (y < YBUT + 30)) {
                javax.swing.JOptionPane.showMessageDialog(this, "GOAL!!!", "BUT", javax.swing.JOptionPane.PLAIN_MESSAGE);
                this.recommencer();
            }
            /*
             if( (x <= 0) && (y > YBUT) && (y < YBUT + 30))
             {
             javax.swing.JOptionPane.showMessageDialog(this,"GOAL!!!", "BUT",javax.swing.JOptionPane.PLAIN_MESSAGE);
             }
    
             */
            if ((y < 3) || (y > YMAX - 33)) {
                System.out.println("touche haut bas");
                deltaY = -deltaY;
            }

            if ((x < 3) || x > XMAX - 33) {
                System.out.println("touche droite gauche!");
                deltaX = -deltaX;
            }

            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {

            }

        }
    }

    public void haut() {
        deltaX = getNbAleat(-16, 16);
        deltaY = getNbAleat(-3, -16);
        demarrer();
    }

    public void bas() {
        deltaX = getNbAleat(-16, 16);
        deltaY = getNbAleat(3, 16);
        demarrer();
    }

    public void patate() {
        deltaX = getNbAleat(-16, 16);
        deltaY = 0;

        demarrer();
    }
    
    public void recommencer()
    {
        deltaX = 0; 
        deltaY = 0;
        x = XMAX /2 - 15;
        y = YMAX / 2 - 15;
        demarrer();
    }

    @Override
    public void paint(java.awt.Graphics comp) {
        java.awt.Graphics2D comp2D = (java.awt.Graphics2D) comp;
        XMAX = getSize().width;
        YMAX = getSize().height;
        if (terrain != null) {
            comp2D.drawImage(terrain, 0, 0, this);
        }
        if (ballon != null) {
            comp2D.drawImage(ballon, x, y, this);
        }
    }

    // les données membres
    Thread th = null;
    java.awt.Image terrain;
    java.awt.Image ballon;
    static int XMIN = 0;
    static int XMAX = 548;
    static int YMIN = 0;
    static int YMAX = 222;
    static int XBUT = XMAX;
    static int YBUT = YMAX / 2 - 15;
    static int P1 = YBUT;
    static int P2 = YBUT + 30;
    int x = XMAX / 2 - 15;
    int y = YMAX / 2 - 15;
    int deltaX;
    int deltaY;
}
